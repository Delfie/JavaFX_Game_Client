
package J_R_C.JOGL.BaseGame;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.Initializable;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * @author KJW finish at 2016/ 02/ 15
 * @version 1.0.0v
 * @description this class Manage The server client
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class ServerClient {

	/**
	 * for the runnable thread
	 */
	private Initializable controller;

	/**
	 * for the login runnable thread
	 */
	private Initializable loginController;

	/**
	 * for the room runnable thread
	 */
	private Initializable gameRoomController;

	/**
	 * client socket
	 */
	private Socket socket;

	/**
	 * if server connection is success the state set using this variable
	 */
	public static final int SERVERCONNECTIONSUCCESS = 1;

	/**
	 * if server connection is ready the state set using this variable
	 */
	public static final int SERVERCONNECTIONREADY = 2;

	/**
	 * if server connection is fail the state set using this variable
	 */
	public static final int SERVERCONNECTIONFAIL = 3;

	/**
	 * this state is for the connection server
	 */
	private int isServerConnected;

	/**
	 * for sending message to server
	 */
	private OutputStream outputStream;

	private Thread thread;

	private Timer msTimer;

	private TimerTask msSecond;

	/**
	 * client name
	 */
	private String clientName;

	/**
	 * client unique game tage number for game token or another event
	 */
	private int clientUniqueGameTagNumber;

	/**
	 * when client's state is start then this method is called
	 */
	public void startClient() {
		isServerConnected = SERVERCONNECTIONREADY;

		thread = new Thread() {
			@Override
			public void run() {

				try {
					socket = new Socket();
					// set the connection first argument is the server IP
					// address and next is port number
					socket.connect(new InetSocketAddress(Settings.sConnectionServerAddress,
							Settings.nConnectionServerPortNumber));
					Platform.runLater(() -> {
						// if the connection is success then this code run
						System.out.println("[Complete the Connection " + socket.getRemoteSocketAddress() + "]");

						isServerConnected = SERVERCONNECTIONSUCCESS;

						sendPacket(Settings._REQUEST_PC_CLIENT_VERSION_CHECK + "", Settings.clientVersion);

						msTimer = new Timer();
						msSecond = new TimerTask() {
							public void run() {
								sendPacket(Settings._ACCESS_THE_SERVER + "");
							}
						};

						// this thread run per 7seconds
						msTimer.schedule(msSecond, 0, 7000);

					});
				} catch (Exception e) {

					System.err.println("you cannot connect the server");
					isServerConnected = SERVERCONNECTIONFAIL;
					if (!socket.isClosed()) {
						stopClient();
					}

				}
				receive();

			}
		};
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();

	}

	/**
	 * set the login controller
	 * 
	 * @param controller
	 */
	public void setController(Initializable controller) {
		this.controller = controller;
	}

	/**
	 * set the room controller
	 * 
	 * @param gameRoomController
	 */
	public void setGameRoomController(Initializable gameRoomController) {
		this.gameRoomController = gameRoomController;
	}

	/**
	 * receive method for the server's packet
	 */
	public void receive() {

		while (true) {

			try {

				byte[] byteArr = new byte[Settings.nReceiveBufferSize];
				InputStream inputStream = socket.getInputStream();
				// 서버가 비정상적으로 종료했을 경우 IOException 발생

				int readByteCount = inputStream.read(byteArr); // 서버가 정상적으로
				// Socket의 close()를 호출했을 경우
				if (readByteCount == -1) {
					throw new IOException();
				}
				String data = new String(byteArr, 0, readByteCount, "UTF-8");

				/*
				 * processing all packet using next method for splitting the
				 * packet
				 */
				String[] multiplePackets = SplitPacketManager.splitMultiplePacket(EncryptionManager.decrypt(data));

				for (int i = 0; i < multiplePackets.length; i++) {

					/*
					 * processing all packet particle using next method for
					 * splitting the packet
					 */
					String[] splitPacket = SplitPacketManager.splitProtocol(multiplePackets[i]);

					int protocol = Integer.parseInt(splitPacket[0]);

					if (protocol != Settings._ANSWER_GAME_ROOM_LIST && protocol != Settings._ANSWER_ROOM_MEMBER_NUMBER)
						System.out.println("receive the Packet " + EncryptionManager.decrypt(data));

					switch (protocol) {
					// if client exit the game room
					case Settings._ANSWER_THE_ROOM_IS_EXIST:
						((WaitingRoomsManagerController) controller).popupTheMessageTheRoomIsExist();
						break;

					// set the room member number in the waiting room
					case Settings._ANSWER_ROOM_MEMBER_NUMBER:
						((WaitingRoomsManagerController) controller).setTheRoomEnteredClient(splitPacket[1]);
						break;
					// set the room list in the waiting room
					case Settings._ANSWER_GAME_ROOM_LIST:
						((WaitingRoomsManagerController) controller).addTheRoom(splitPacket);
						break;
					// set the clicked event in the waiting room
					case Settings._ANSWER_CLICKED_ROOM_INFORMATION:
						((WaitingRoomsManagerController) controller).setTheRoomImformation(splitPacket);
						break;
					// set the message event in the waiting room
					case Settings._ANSWER_WAITING_ROOM_SENDING_MESSAGE:
						if (controller instanceof WaitingRoomsManagerController)
							((WaitingRoomsManagerController) controller).setTheRoomMessage(splitPacket);
						break;
					// make the new room in the waiting room
					case Settings._ANSWER_THE_ROOM_MAKE_SUCCESS:
						((WaitingRoomsManagerController) controller).enterTheNewGameRoom(splitPacket);
						break;
					// answer about client login
					case Settings._ANSWER_LOGIN:
						setClientName(splitPacket[2]);
						((LoginController) loginController).loginToTheMainRobby(splitPacket);
						break;
					// answer about that client is successfully entered the
					// game room
					case Settings._ANSWER_GUEST_ENTER_THE_ROOM_SUCCESS:
						((WaitingRoomsManagerController) controller).enterTheNewGameRoom(splitPacket);
						break;
					// answer the current game room member number
					case Settings._ANSWER_GAME_ROOM_MEMEBER_NUMBER:
						((GameRoomController) gameRoomController).setTheGameRoomMemberNumber(splitPacket);
						break;
					// answer the on client out of the room event
					case Settings._ANSWER_OUT_OF_THE_ROOM:
						((GameRoomController) gameRoomController).outOfTheRoom();
						break;
					// set the message event in the game room
					case Settings._ANSWER_ROOM_MEMEBER_MESSAGE:
						if (gameRoomController instanceof GameRoomController)
							((GameRoomController) gameRoomController).setTheRoomMessage(splitPacket);
						break;
					// notify that new member enter the room
					case Settings._ANSWER_NEW_ROOM_MEMEBER_NOTIFICATION:
						((GameRoomController) gameRoomController).notificationNewRoomMemeberMessage(splitPacket);
						break;
					// answer that the client can not enter the game room
					case Settings._ANSWER_GUEST_ENTER_THE_ROOM_FAIL:
						((WaitingRoomsManagerController) controller)
								.handlePopup("[" + splitPacket[1] + "] 인원이 가득찼습니다.");
						break;
					// if client click the game start button. then client
					// can have the this protocol
					case Settings._ANSWER_START_THE_GAME:
						((GameRoomController) gameRoomController).gameSetReady(splitPacket);
						break;
					// if all of member click the game start button. then
					// this protocol is received
					case Settings._ANSWER_ROOM_GAME_START:
						((GameRoomController) gameRoomController).gameRoomSetReady(splitPacket);
						break;
					// if the client entered the game room, then the client
					// can get game unique tag number
					case Settings._ANSWER_SET_GAME_PLAYER_UNIQUE_TAG_NUMBER:
						setClientUniqueGameTagNumber(Integer.parseInt(splitPacket[1]));
						break;
					// answer TicTacToc game click event
					case Settings._ANSWER_TICTACTOC_STONE_EVENT:
						((GameRoomController) gameRoomController).ticTactocPlay(splitPacket);
						break;
					// answer TicTacToc user's name who currently have play
					// token
					case Settings._ANSWER_TICTACTOC_TURN_PLAYER_NAME:
						((GameRoomController) gameRoomController).setTurnPlayerName(splitPacket);
						break;
					// if game state is end, this protocol is received
					case Settings._ANSWER_GAME_END:
						((GameRoomController) gameRoomController).endOfTheGame(splitPacket);
						break;
					// set the all client game end state
					case Settings._ANSWER_GAME_END_REVERSE:
						((GameRoomController) gameRoomController).endOfTheGame(splitPacket);
						break;
					// if new client request registering. then this protocol
					// is received
					case Settings._ANSWER_REGISTER_NEW_USER:
						((LoginController) loginController).checkTheNewUserRegisterSuccess(splitPacket);
						break;
					// set the TicTacToc AI
					case Settings._ANSWER_TICTACTOC_AI:
						((GameRoomController) gameRoomController).ticTactocPlay(splitPacket);
						break;
					// set the TicTacToc Color Mode
					case Settings._ANSWER_SET_THE_TICTACTOC_COLOR_MODE:
						((GameRoomController) gameRoomController).ticTacTocGameColorSetting(splitPacket);
						break;
					// answer about TicTacToc AI
					case Settings._ANSWER_TICTACTOC_AI_SETTING:
						((GameRoomController) gameRoomController).ticTacTocGameAISetting(splitPacket);
						break;
					// answer CatchMe Selected Item 0~3
					case Settings._ANSWER_CATCHME_SELECT_ITEM:
						((GameRoomController) gameRoomController).catchMeItemSetting(splitPacket);
						break;
					// answer CatchMe Click event
					case Settings._ANSWER_CATCHME_STONE_EVENT:
						((GameRoomController) gameRoomController).catchmePlay(splitPacket);
						break;
					// answer CatchMe Game Init State about Manager
					case Settings._ANSWER_CATCHME_INIT_PLAY_CLICKED_NUMBER:
						((GameRoomController) gameRoomController).catchmeClickPlayNumberSet(splitPacket);
						break;
					// draw the CatchMe Path, at end of Game
					case Settings._ANSWER_CATCHME_DRAW_PATH:
						((GameRoomController) gameRoomController).catchmeReDrawMap(splitPacket);
						break;
					// set The CatchMe User's Click Count
					case Settings._ANSWER_SET_THE_CATCHME_PLAY_COUNT:
						((GameRoomController) gameRoomController).setCatchmePlayCount(splitPacket);
						break;

					case Settings._ANSWER_METEORGAME_SET_CLIECK_EVENT:
						((GameRoomController) gameRoomController).setMeteorGamePlayerPosition(splitPacket);
						break;

					case Settings._ANSWER_METEORGAME_INIT_GAME_PLAY:
						((GameRoomController) gameRoomController).initMeteorGamePlayerGamePosition(splitPacket);
						break;

					case Settings._ANSWER_METEORGAME_REINIT_GAME_PLAY:
						((GameRoomController) gameRoomController).initREMeteorGamePlayerGamePosition(splitPacket);
						break;

					case Settings._ANSWER_METEORGAME_OUT_OF_PLAYER:
						if (!splitPacket[1].equals(getClientName()))
							((GameRoomController) gameRoomController).outOfPlayerInMeteorGame(splitPacket);
						break;

					case Settings._ANSWER_METEORGAME_UNIVERSE_INIT:
						((GameRoomController) gameRoomController).initMeteorGameWhenStartGame(splitPacket);
						break;

					case Settings._ANSWER_METEORGAME_PLAYER_MOVING:
						((GameRoomController) gameRoomController).updateMeteorGamePlayerPosition(splitPacket);
						break;

					case Settings._ANSWER_METEORGAME_PLAY_START:
						((GameRoomController) gameRoomController).StartPrepareCompleteMeteorGame(splitPacket);
						break;

					case Settings._ANSWER_METEORGAME_METEOR_DELETE:
						((GameRoomController) gameRoomController).deleteMeteorGameMeteor(splitPacket);
						break;

					case Settings._ANSWER_METEORGAME_METEOR_GAME_FINISH:
						((GameRoomController) gameRoomController).setFinishMeteorGame();
						break;

					case Settings._ANSWER_PC_CLIENT_VERSION_CHECK:
						((LoginController) loginController).clientVersionCheck(splitPacket);
						break;

					// process unExist protocols
					default:
						System.err.println("[" + protocol + "] this protocol do not exist in the protocol list");
						break;
					}
				}

			} catch (Exception e) {
				isServerConnected = SERVERCONNECTIONFAIL;
				System.err.println("[" + e + "] you cannot connect the server - receiving Error");
				stopClient();
				break;

			}

		}

	}

	/**
	 * send message to the game server
	 * 
	 * @param partitionPacketNumber
	 * @param datas
	 */
	public void sendPacket(String... datas) {
		String packet = new String();

		packet = packet.concat(Settings.sSenderSplitProtocolToken);

		for (int i = 0; i < datas.length; i++) {
			packet = packet.concat(datas[i] + Settings.sSenderSplitProtocolToken);
		}
		packet = packet.concat(Settings.sSenderSplitMultipleToken);

		send(packet);
	}

	/**
	 * send packet
	 * 
	 * @param data
	 */
	public void send(String data) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					byte[] byteArr = EncryptionManager.encrypt(data).getBytes("UTF-8");
					outputStream = socket.getOutputStream();
					outputStream.write(byteArr);
					outputStream.flush();
					// System.out.println("[보내기 완료]");
				} catch (Exception e) {
					System.err.println("[서버 통신 안됨] : " + e);
					stopClient();
				}
			}
		};
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
	}

	/**
	 * if client connection's state is terminate, then this method is called
	 */
	public void stopClient() {
		try {
			Platform.runLater(() -> {
				System.err.println("[Force to Terminate the Connection - Please ReTry Connection]");

			});
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}

			if (thread != null && thread.isAlive())
				thread.interrupt();
			if (msTimer != null) {
				msTimer.cancel();
				msTimer = null;
			}
			if (msSecond != null) {
				msSecond.cancel();
				msSecond = null;
			}

			isServerConnected = SERVERCONNECTIONFAIL;

		} catch (IOException e) {
		}
	}

	/**
	 * get client socket
	 * 
	 * @return
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * get client login controller
	 * 
	 * @return
	 */
	public Initializable getLoginController() {
		return loginController;
	}

	/**
	 * set client's login controller
	 * 
	 * @param loginController
	 */
	public void setLoginController(Initializable loginController) {
		this.loginController = loginController;
	}

	/**
	 * get server connection state
	 * 
	 * @return
	 */
	public int getIsServerConnected() {
		return isServerConnected;
	}

	/**
	 * set server connection state
	 * 
	 * @param isServerConnected
	 */
	public void setIsServerConnected(int isServerConnected) {
		this.isServerConnected = isServerConnected;
	}

	/**
	 * get client name stiring
	 * 
	 * @return
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * set client name
	 * 
	 * @param clientName
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * get client unique game tag number integer
	 * 
	 * @return
	 */
	public int getClientUniqueGameTagNumber() {
		return clientUniqueGameTagNumber;
	}

	/**
	 * set client unique game tag number
	 * 
	 * @param clientUniqueGameTagNumber
	 */
	public void setClientUniqueGameTagNumber(int clientUniqueGameTagNumber) {
		this.clientUniqueGameTagNumber = clientUniqueGameTagNumber;
	}

}
