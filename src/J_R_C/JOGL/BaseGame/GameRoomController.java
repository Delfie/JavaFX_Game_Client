
package J_R_C.JOGL.BaseGame;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import GraphicUtility.AnimationManager;
import GraphicUtility.AnimationSprite;
import GraphicUtility.GraphicsContextSprite;
import GraphicUtility.Sprite;
import Object.Enemy_Missile;
import Object.Explosion_Effect;
import Object.PangPangEnemy;
import Object.PangPangPlayer;
import Object.Player;
import Utility.Sound_Audio;
import Utility.Sound_Music;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * @author KJW finish at 2016/ 08/ 14
 * @version 2.0.0v
 * @description this class Manage The all the game window's state
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class GameRoomController implements Initializable {

	/**
	 * sound controller for music -background
	 */
	private Sound_Music soundPangPangBackground;

	/**
	 * sond controller for audio -for effect sound
	 */
	private Sound_Audio effectSoundManager;

	/**
	 * sending message maximum length
	 */
	private final int SENDINGMESSAGEMAXLENGTH = 70;

	/**
	 * decemal format changer
	 */
	DecimalFormat df;

	/**
	 * this button for exiting the game room window
	 */
	@FXML
	private Button btnCancel;

	/**
	 * this button for ready game or stop
	 */
	@FXML
	private Button btnStartCancel;

	/**
	 * this label for the game room's name
	 */
	@FXML
	private Label lbRoomName;

	/**
	 * this label for the game room's maximum number
	 */
	@FXML
	private Label lbMaximumMember;

	/**
	 * this textField for the insert room clients messages
	 */
	@FXML
	private TextField cheatingTextEdit;

	/**
	 * this textArea show the clients message
	 */
	@FXML
	private TextArea cheatingTextArea;

	/**
	 * this lAbel Show the what is the game style about this game room
	 */
	@FXML
	private Label lbGameStyle;

	/**
	 * this button for sending message
	 */
	@FXML
	private Button btnSendMessage;

	/**
	 * this pane for drawing game images
	 */
	@FXML
	private AnchorPane anchorPane;

	/**
	 * Main Pane for game control
	 */
	@FXML
	private AnchorPane mainPane;

	/**
	 * this label show the currently have play token's client name
	 */
	@FXML
	private Label lbPlayerTurn;

	/**
	 * this ImageView show the iamge about game item for client
	 */
	@FXML
	private ImageView imageGameMainView;

	/**
	 * this Label show the next Player name
	 */
	@FXML
	private Label lbPlayerTurnText;

	/**
	 * this Label Show the possible Click Event about CathchMe Game
	 */
	@FXML
	private Label lbCatchmePlayCount;

	/**
	 * player turn label
	 */
	@FXML
	private Label lbPlayerTurnBlockText;

	/**
	 * for saving primary Stage ( change Scene)
	 */
	private Stage primaryStage;

	/**
	 * for Storing the Game room's Name
	 */
	private String sRoomName;

	/**
	 * for Storing the ToTal Client number
	 */
	private int nToTalClient;

	/**
	 * for storing the Game Type number
	 */
	private int nGameType;

	/**
	 * for storing the client
	 */
	private ServerClient client;

	/**
	 * this boolean indicate the now game start state
	 */
	private boolean isGameStart;

	/**
	 * set the main game view max width size
	 */
	private double GameViewMaximumWidth = 360;

	/**
	 * set the main game view max height size
	 */
	private double GameViewMaximumHeight = 290;

	/**
	 * this boolean means that this client has a play token
	 */
	private boolean isPlayToken;

	/**
	 * for storing entered client member
	 */
	private int nEnteredMember;

	/**
	 * this boolean means that color mode about TicTacToc
	 */
	private boolean isTicTacTocTwoColorMode;

	/**
	 * for show the TicTacToc multiple board color
	 */
	private Sprite ticTacTocMultipeColorboard[];

	/**
	 * this variable indicates the catchMe Now Item number
	 */
	private int nCatchmeItem;

	/**
	 * this image is default image about imageGameMainView
	 */
	private Image imageLogo;

	/**
	 * this variable indicates the possible game play number (catchMe)
	 */
	private int nPossiblePlayNumber;

	/**
	 * spriter animation timer. call this class per time decided programmer
	 */
	private AnimationTimer spriteAnimationTimer;

	/**
	 * input event container
	 */
	private ArrayList<String> input = new ArrayList<String>();

	/**
	 * meteorgame player array list
	 */
	private ArrayList<Player> players = new ArrayList<Player>();

	/**
	 * pangpang player array list
	 */
	private ArrayList<PangPangPlayer> pangPangPlayers = new ArrayList<PangPangPlayer>();

	/**
	 * meteoger game asteroids array lisy
	 */
	private ArrayList<Player> asteroids = new ArrayList<Player>();

	/**
	 * pangpang game enemy buubles array list
	 */
	private ArrayList<PangPangEnemy> bubbles = new ArrayList<PangPangEnemy>();

	/**
	 * pangpang bubbles crash effect
	 */
	private ArrayList<Explosion_Effect> pangpang_bubbles_effects = new ArrayList<Explosion_Effect>();

	/**
	 * pangpang bubbles missile
	 */
	private ArrayList<Enemy_Missile> bubbles_missiles = new ArrayList<Enemy_Missile>();

	/**
	 * pangpang playsr missiles
	 */
	private ArrayList<Enemy_Missile> player_Missiles = new ArrayList<Enemy_Missile>();

	/**
	 * meteor game main player
	 */
	private Player clientMainPlayer;

	/**
	 * pangapng main player
	 */
	private PangPangPlayer clientPangPangMainPlayer;

	/**
	 * meteor game start position x
	 */
	private double meteriorGamePlayerPositionX;

	/**
	 * meteor game start position y
	 */
	private double meteriorGamePlayerPositionY;

	/**
	 * meteor game prepare finish tag
	 */
	private boolean isMeteorGameStartPrepareFinish;

	/**
	 * pangpang game prepare finish tag
	 */
	private boolean isPangPangStartPrepareFinish;

	/**
	 * meteor game astroy destroyed count
	 */
	private int nMeteorGameDestroyCount;

	/**
	 * meteor game end finish check tag
	 */
	private boolean isMeteorGameFinishCheck;

	/**
	 * meteor game winner id
	 */
	private String sNowMeteorGameWinner;

	/**
	 * shell command saving container
	 */
	private String[] sCommandsContainer;

	/**
	 * comment list indicator
	 */
	private int nCommandIndicatorPoisition;

	/**
	 * comment container indicator
	 */
	private int nCommandsContainerIndicator;

	/**
	 * first player position
	 */
	int _firstPoistion;

	/**
	 * init tag
	 */
	private boolean isInit = false;

	/**
	 * pangoang enemy stack
	 */
	private String sPangPangEnemyStack;

	/**
	 * pangpang stack running tag
	 */
	private boolean isPangPangEnemyStackRunning;

	/**
	 * pangapng score
	 */
	private int nPangPangScore;

	/**
	 * roomnumber initialize
	 */
	private long nInitRoomNumber;

	Random rnd;

	// = new GraphicsContextSprite("box.png", 100, 100);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle) init the all state about games
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		soundPangPangBackground = new Sound_Music(Settings.saPangPangBGM);
		sNowMeteorGameWinner = null;
		isMeteorGameFinishCheck = true;
		isPangPangEnemyStackRunning = false;
		isMeteorGameStartPrepareFinish = false;
		isPangPangStartPrepareFinish = false;
		meteriorGamePlayerPositionX = Settings.ZEROINIT;
		meteriorGamePlayerPositionY = Settings.ZEROINIT;
		nMeteorGameDestroyCount = Settings.ZEROINIT;
		nPangPangScore = Settings.ZEROINIT;
		this.setGameStart(false);
		lbCatchmePlayCount.setVisible(false);
		lbPlayerTurn.setVisible(false);
		df = new DecimalFormat("#.##");

		sCommandsContainer = new String[Settings.nMaximumSizeOfCommandsContainer];

		effectSoundManager = new Sound_Audio(Settings.nEffectPoolSize);

		effectSoundManager.load_Sound_Effects(Settings.saCrashEffect, Settings.saPangPangCrashBGM);

		// game play board size
		anchorPane.setMaxSize(GameViewMaximumWidth, GameViewMaximumHeight);
		// register click event listener
		anchorPane.setOnMouseClicked(e -> gameWindowsClickedEvent(e));
		// register click event listener
		btnCancel.setOnAction(e -> handleBtnCancel(e));
		btnStartCancel.setOnAction(e -> handlebtnStartStop(e));

		mainPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				meteorGameInputKeyManager(e);
			}
		});

		mainPane.setOnKeyReleased(e -> handleBtnKeyReleaseEvent(e));

		client = LoginController.getClient();
		client.setGameRoomController(this);

		lbPlayerTurnText.setStyle("-fx-font: 12 arial;");

		// set the variable about cheatingTextArea
		cheatingTextEdit.addEventFilter(KeyEvent.KEY_TYPED, message_text_Validation(SENDINGMESSAGEMAXLENGTH));
		cheatingTextArea.setEditable(false);
		cheatingTextArea.setStyle(
				"-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color;-fx-background-insets: 0, 1, 2;-fx-background-radius: 5, 4, 3;");

		btnSendMessage.setOnAction(e -> handleBtnSendingMessage(e));
		cheatingTextEdit.setOnKeyPressed(e -> handleBtnKeyEvent(e));
		cheatingTextEdit.setOnKeyReleased(e -> handleBtnKeyReleaseEvent(e));
		cheatingTextArea.requestFocus();

		isTicTacTocTwoColorMode = false;
		ticTacTocMultipeColorboard = new Sprite[8];

		// set the default image about imageGameMainView
		File file = new File("src/Asset/img_pangpang_32.png");
		imageLogo = new Image(file.toURI().toString());
		imageGameMainView.setImage(imageLogo);
		nCatchmeItem = -1;

		rnd = new Random();

		// Game updatePart and all time check server condition and image state
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		scheduler.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {

				Platform.runLater(new Runnable() {
					@Override
					public void run() {

						if (client.getIsServerConnected() == ServerClient.SERVERCONNECTIONFAIL) {
							terminate();
							handlePopup("서버와의 연결이 끊겼습니다.");
							scheduler.shutdown();

						}

						if (getnGameType() == Settings.nGameCatchMe && Settings.ERRORCODE == getnCatchmeItem()) {
							imageGameMainView.setImage(imageLogo);
							imageGameMainView.setViewport(new Rectangle2D(0, 0, 32, 32));
						}
					}
				});

			}
		}, 100, 100, TimeUnit.MILLISECONDS);

	}

	/**
	 * if terminate event occurred. then this event is called. ( return the
	 * LoginSecene)
	 */
	public void terminate() {
		if (getnGameType() == Settings.nGameMeteorGame)
			spriteAnimationTimer.stop();
		else if (getnGameType() == Settings.nGamePangPang) {
			spriteAnimationTimer.stop();
			soundPangPangBackground.stopMusic();
			effectSoundManager.turnoff();
		}
		Stage stage = (Stage) primaryStage.getScene().getWindow();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/root.fxml"));
			Parent loginScene = (Parent) loader.load();
			Scene scene = new Scene(loginScene);
			scene.getStylesheets().add(getClass().getResource("/Css/app.css").toString());
			LoginController loginController = loader.getController();
			loginController.setPrimaryStage(primaryStage);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * if client click the game board this event is occurred
	 * 
	 * @param event
	 */
	public void gameWindowsClickedEvent(MouseEvent event) {

		// check the event type (TicTacToc, CatchMe) and parse The Position to
		// x,y

		if (isGameStart() && getnGameType() == Settings.nGameTicTacToc && isPlayToken() == true) {

			for (int i = 0; i < anchorPane.getChildren().size(); i++) {
				final double checkX = anchorPane.getChildren().get(i).getLayoutX();
				final double checkY = anchorPane.getChildren().get(i).getLayoutY();
				anchorPane.getChildren().get(i).addEventHandler(MouseEvent.MOUSE_CLICKED,
						e -> tictactocClieckEvent(e, checkX, checkY));
			}
		}

		if (isGameStart() && getnGameType() == Settings.nGameCatchMe && isPlayToken() == true) {

			for (int i = 0; i < anchorPane.getChildren().size(); i++) {
				final double checkX = anchorPane.getChildren().get(i).getLayoutX();
				final double checkY = anchorPane.getChildren().get(i).getLayoutY();
				anchorPane.getChildren().get(i).addEventHandler(MouseEvent.MOUSE_CLICKED,
						e -> catchmeClieckEvent(e, checkX, checkY));
			}
		}

	}

	/**
	 * handle the CatchMe Click event
	 * 
	 * @param event
	 * @param x
	 * @param y
	 */
	public void catchmeClieckEvent(MouseEvent event, double x, double y) {
		if (isGameStart() && getnGameType() == Settings.nGameCatchMe && isPlayToken() == true) {
			int _x = (int) x / Settings.nCatchMeImageWidth;
			int _y = (int) y / Settings.nCatchMeImageHeight;

			client.sendPacket(Settings._REQUEST_CATCHME_STONE_EVENT + "", getnInitRoomNumber() + "", _x + "", _y + "",
					getnCatchmeItem() + "");

			setPlayToken(false);
			event.consume();
		}
	}

	/**
	 * handle the TicTacToc Click event
	 * 
	 * @param event
	 * @param x
	 * @param y
	 */
	public void tictactocClieckEvent(MouseEvent event, double x, double y) {
		if (isGameStart() && getnGameType() == Settings.nGameTicTacToc && isPlayToken() == true) {
			int _x = (int) x / Settings.nTicTacTocImageWidth;
			int _y = (int) y / Settings.nTicTacTocImageHeight;

			client.sendPacket(Settings._REQUEST_TICTACTOC_STONE_EVENT + "", getnInitRoomNumber() + "", _x + "",
					_y + "");

			setPlayToken(false);
			event.consume();
		}
	}

	/**
	 * draw game image on the gameboard (anchorpane)
	 * 
	 * @param sprite
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	private void drawImage(Sprite sprite, double x, double y, double width, double height) {
		Image image = new Image(sprite.getImageURL());
		ImageView imageview = new ImageView(image);

		double _imageStartX = sprite.getImageStartX();
		double _imageStartY = sprite.getImageStartY();

		double _imageWidth = sprite.getImageWidth();
		double _imageHeight = sprite.getImageHeight();
		double xPersantage;
		double yPersantage;

		double newWidth = width;
		double newHeight = height;

		imageview.setLayoutX(x);
		imageview.setLayoutY(y);

		if (x < 0) {
			xPersantage = (-x) / width;
			_imageStartX = _imageWidth * xPersantage;
			_imageWidth = _imageWidth * xPersantage;
			newWidth = width * xPersantage;
			imageview.setLayoutX(0);
		} else if (x > GameViewMaximumWidth - width) {
			xPersantage = -(x - GameViewMaximumWidth) / width;
			_imageWidth = _imageWidth - _imageWidth * xPersantage;
			newWidth = width * xPersantage;
			imageview.setLayoutX(GameViewMaximumWidth - width * xPersantage);
		}
		if (y < 0) {
			yPersantage = (-y) / height;
			_imageStartY = _imageHeight * yPersantage;
			_imageHeight = _imageHeight * yPersantage;
			newHeight = height * yPersantage;
			imageview.setLayoutY(0);
		} else if (y > GameViewMaximumHeight - height) {
			yPersantage = -(y - GameViewMaximumHeight) / height;
			_imageHeight = _imageHeight * yPersantage;
			newHeight = height * yPersantage;
			imageview.setLayoutY(GameViewMaximumHeight - height * yPersantage);
		}

		imageview.setViewport(new Rectangle2D(_imageStartX, _imageStartY, _imageWidth, _imageHeight));

		imageview.setFitWidth(newWidth);
		imageview.setFitHeight(newHeight);

		anchorPane.getChildren().add(imageview);
	}

	/**
	 * draw pangapng game -managing every thing related on pangapng
	 */
	private void drawSpriteImageViewPangPang() {
		client.sendPacket(Settings._REQUEST_PANGPANG_INIT_GAME_PLAY + "", getnInitRoomNumber() + "",
				client.getClientName());

		Canvas canvas = new Canvas(360, 280);

		anchorPane.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		GraphicsContextSprite background = new GraphicsContextSprite("/Asset/back02.png", 2182, 0, 1091, 672);

		PangPangPlayer.setPlayerLeftImage(
				new AnimationManager("/Asset/pangpang_charecter.png", new AnimationSprite(100, 0, 28, 56),
						new AnimationSprite(200, 0, 28, 56), new AnimationSprite(300, 0, 28, 56)));

		PangPangPlayer.getPlayerLeftImage().setAnimationChangeTime(0.2);

		PangPangPlayer.setPlayerRightImage(
				new AnimationManager("/Asset/pangpang_charecter.png", new AnimationSprite(150, 0, 28, 56),
						new AnimationSprite(250, 0, 28, 56), new AnimationSprite(350, 0, 28, 56)));

		PangPangPlayer.getPlayerRightImage().setAnimationChangeTime(0.2);

		GraphicsContextSprite lifeImage = new GraphicsContextSprite("/Asset/life00.png", 0, 0, 29, 29);

		initPangPangBubbles();
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 10);

		/*
		 * pangapng Game mainbody
		 */
		spriteAnimationTimer = new AnimationTimer() {

			boolean isGameRunning = true;
			Long lastNanoTime = new Long(System.nanoTime());

			public void handle(long currentNanoTime) {
				// calculate time since last update.
				double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
				lastNanoTime = currentNanoTime;

				// init part
				if (isGameRunning == false)
					return;

				if (isInit == true) {
					isInit = false;

					pangpangReceiveEnemyInit();

				}

				gc.clearRect(0, 0, Settings.fSPRITEGAMEWIDTH, Settings.fSPRITEGAMEHEIGHT);
				background.render(gc, Settings.nGameAsteroidSceneWidth / 2, Settings.nGameAsteroidSceneHeight / 2,
						Settings.nGameAsteroidSceneWidth, Settings.nGameAsteroidSceneHeight);

				// // game logic
				if (isPangPangStartPrepareFinish && isGameStart() && false == clientPangPangMainPlayer.isDeath()) {

					boolean isClick = false;

					// input event processor
					if (input.contains("LEFT")) {
						if (clientPangPangMainPlayer.getDirection() != GraphicsContextSprite.LEFT) {
							clientPangPangMainPlayer.setDirection(GraphicsContextSprite.LEFT);
							isClick = true;
						}
					}
					if (input.contains("RIGHT")) {
						if (clientPangPangMainPlayer.getDirection() != GraphicsContextSprite.RIGHT) {
							clientPangPangMainPlayer.setDirection(GraphicsContextSprite.RIGHT);
							client.sendPacket(Settings._REQUEST_PANGPANG_PLAYER_MOVING + "", getnInitRoomNumber() + "",
									client.getClientName(), clientPangPangMainPlayer.getDirection() + "");

							isClick = true;
						}
					}
					if (input.contains("UP")) {
						if (clientPangPangMainPlayer.getDirection() != GraphicsContextSprite.UP) {
							clientPangPangMainPlayer.setDirection(GraphicsContextSprite.UP);
							Runnable runnable = new Runnable() {
								@Override
								public void run() {
									client.sendPacket(Settings._REQUEST_PANGAPNG_ATTACK + "", getnInitRoomNumber() + "",
											client.getClientName(), client.getClientName());
								}
							};
							executorService.submit(runnable);
							isClick = true;
						}
					}

					if (isClick) {
						Runnable runnable = new Runnable() {
							@Override
							public void run() {
								client.sendPacket(Settings._REQUEST_PANGPANG_PLAYER_MOVING + "",
										getnInitRoomNumber() + "", client.getClientName(),
										clientPangPangMainPlayer.getDirection() + "");
							}
						};
						executorService.submit(runnable);

						isClick = false;
					}
				} else if (clientPangPangMainPlayer != null)
					clientPangPangMainPlayer.setVelocity(0, 0);

				// colission detection
				for (int i = 0; i < player_Missiles.size(); i++) {
					player_Missiles.get(i).update(elapsedTime);

					if (player_Missiles.get(i).getPositionY() <= -10) {
						player_Missiles.remove(i);
						continue;
					}

					for (int j = 0; j < bubbles.size(); j++) {
						if (bubbles.get(j).getPositionX() != 0 && bubbles.get(j).getPositionY() != 0
								&& bubbles.get(j).intersects(player_Missiles.get(i))) {
							player_Missiles.remove(i);
							effectSoundManager.play_Effect_Sound(Settings.saCrashEffect);
							client.sendPacket(Settings._REQUEST_PANGAPNG_ENEMY_COLLISION_EVENT + "",
									getnInitRoomNumber() + "", bubbles.get(j).getsPlayerName());

							if (player_Missiles.get(i).getsNameID().equals(client.getClientName())) {
								nPangPangScore += rnd.nextInt(300) + 200;
								Platform.runLater(() -> {
									lbPlayerTurn.setText(nPangPangScore + "");
								});
							}

						}
					}
				}

				for (int i = 0; i < bubbles_missiles.size(); i++) {
					bubbles_missiles.get(i).update(elapsedTime);

					for (int j = 0; j < pangPangPlayers.size(); j++) {

						if (pangPangPlayers.get(j).isDeath() == false
								&& pangPangPlayers.get(j).intersects(bubbles_missiles.get(i))) {
							bubbles_missiles.remove(i);
							pangPangPlayers.get(j).decreaseLife();

							if (pangPangPlayers.get(j).isDeath()) {

								displayText("[" + pangPangPlayers.get(j).getsPlayerName() + "] is death");
								client.sendPacket(Settings._REQUEST_PANGAPNG_PLAYER_DEATH + "",
										getnInitRoomNumber() + "", pangPangPlayers.get(j).getsPlayerName());

							}
						}
					}

					if (bubbles_missiles.get(i).getPositionY() >= Settings.nGameAsteroidSceneHeight + 10)
						bubbles_missiles.remove(i);

				}

				for (int i = 0; i < pangpang_bubbles_effects.size(); i++) {
					pangpang_bubbles_effects.get(i).update(elapsedTime);

					if (pangpang_bubbles_effects.get(i).isTermination()) {
						pangpang_bubbles_effects.remove(i);
						continue;
					}
				}

				for (int i = 0; i < pangPangPlayers.size(); i++) {
					if (false == pangPangPlayers.get(i).isDeath()) {
						pangPangPlayers.get(i).update(elapsedTime);
						checkBound(pangPangPlayers.get(i));
					}

				}

				// game termination

				if (pangPangPlayers.size() <= 0) {
					displayText("Game OVER!!");
					client.sendPacket(Settings._REQUEST_PANGAPNG_FINISH + "", getnInitRoomNumber() + "",
							client.getClientName());
					isGameRunning = false;
				}

				if (bubbles.size() == 8) {
					displayText("Game Win!!");
					soundPangPangBackground.stopMusic();
					client.sendPacket(Settings._REQUEST_PANGAPNG_FINISH_WIN + "", getnInitRoomNumber() + "",
							client.getClientName(), nPangPangScore + "");
					isGameRunning = false;
				}

				// render

				for (int i = 0; i < pangPangPlayers.size(); i++) {
					if (false == pangPangPlayers.get(i).isDeath()) {
						if (pangPangPlayers.get(i).getDirection() == GraphicsContextSprite.RIGHT) {
							pangPangPlayers.get(i);
							PangPangPlayer.getPlayerLeftImage().renderAnimation(elapsedTime, gc,
									pangPangPlayers.get(i).getPositionX(), pangPangPlayers.get(i).getPositionY(), 17,
									30);
						} else if (pangPangPlayers.get(i).getDirection() == GraphicsContextSprite.LEFT) {
							pangPangPlayers.get(i);
							PangPangPlayer.getPlayerRightImage().renderAnimation(elapsedTime, gc,
									pangPangPlayers.get(i).getPositionX(), pangPangPlayers.get(i).getPositionY(), 17,
									30);
						} else if (pangPangPlayers.get(i).getDirection() == GraphicsContextSprite.UP)
							pangPangPlayers.get(i).render(gc);
					}
				}

				if (isPangPangStartPrepareFinish) {
					pangPangEnemyPositionProcess();

					for (int i = 0; i < bubbles.size(); i++) {
						if (bubbles.get(i).getPositionX() != 0 || bubbles.get(i).getPositionY() != 0)
							bubbles.get(i).render(gc);
					}

					for (int i = 0; i < bubbles_missiles.size(); i++)
						bubbles_missiles.get(i).render(gc);

					for (int i = 0; i < player_Missiles.size(); i++)
						player_Missiles.get(i).render(gc);

					for (int i = 0; i < pangpang_bubbles_effects.size(); i++)
						pangpang_bubbles_effects.get(i).render(gc);

					for (int i = 0; i < clientPangPangMainPlayer.getnLife(); i++)
						lifeImage.render(gc, 10 + i * 20, 10, 20, 20);
				}

			}
		};

		spriteAnimationTimer.start();

	}

	/**
	 * managing MeteorGame
	 */
	private void drawSpriteImageViewMeteor() {
		client.sendPacket(Settings._REQUEST_METEORGAME_INIT_GAME_PLAY + "", getnInitRoomNumber() + "",
				client.getClientName());

		// Mapping 사영 시키는 함수 만들것.
		Canvas canvas = new Canvas(360, 280);

		anchorPane.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		// sending click event
		anchorPane.getScene().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				client.sendPacket(Settings._REQUEST_METEORGAME_SET_CLIECK_EVENT + "", getnInitRoomNumber() + "",
						e.getX() - 100 + "", e.getY() - 14 + "");

			}
		});

		GraphicsContextSprite background = new GraphicsContextSprite("/Asset/space.jpg", 0, 0, 640, 480);

		/*
		 * proecessing everything on this parts
		 */
		spriteAnimationTimer = new AnimationTimer() {

			Long lastNanoTime = new Long(System.nanoTime());

			public void handle(long currentNanoTime) {
				// calculate time since last update.
				double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
				lastNanoTime = currentNanoTime;

				// game logic
				if (isMeteorGameStartPrepareFinish && isGameStart()) {

					/*
					 * click event
					 */
					if (input.contains("LEFT"))
						clientMainPlayer.addVelocity(-3, 0);
					if (input.contains("RIGHT"))
						clientMainPlayer.addVelocity(3, 0);
					if (input.contains("UP"))
						clientMainPlayer.addVelocity(0, -3);
					if (input.contains("DOWN"))
						clientMainPlayer.addVelocity(0, 3);
				} else if (clientMainPlayer != null)
					clientMainPlayer.setVelocity(0, 0);

				// update parts
				if (clientMainPlayer != null) {
					clientMainPlayer.update(elapsedTime);

					checkBound(clientMainPlayer);

					if (Math.abs(meteriorGamePlayerPositionX - clientMainPlayer.getPositionX()) > 1.2f
							|| Math.abs(meteriorGamePlayerPositionY - clientMainPlayer.getPositionY()) > 0.5) {
						client.sendPacket(Settings._REQUEST_METEORGAME_PLAYER_MOVING + "", getnInitRoomNumber() + "",
								client.getClientName(), clientMainPlayer.getPositionX() + "",
								clientMainPlayer.getPositionY() + "");
						meteriorGamePlayerPositionX = clientMainPlayer.getPositionX();
						meteriorGamePlayerPositionY = clientMainPlayer.getPositionY();
					}
				}

				// render

				gc.clearRect(0, 0, Settings.fSPRITEGAMEWIDTH, Settings.fSPRITEGAMEHEIGHT);
				background.render(gc, Settings.nGameAsteroidSceneWidth / 2, Settings.nGameAsteroidSceneHeight / 2,
						Settings.nGameAsteroidSceneWidth, Settings.nGameAsteroidSceneHeight);

				for (int i = 0; i < players.size(); i++) {
					players.get(i).render(gc);
				}

				for (int i = 0; i < asteroids.size(); i++) {
					asteroids.get(i).render(gc);

					// collision detection
					for (int j = 0; j < players.size(); j++) {
						if (players.get(j).intersects(asteroids.get(i)) && isMeteorGameStartPrepareFinish) {

							client.sendPacket(Settings._REQUEST_METEORGAME_METEOR_DELETE + "",
									getnInitRoomNumber() + "", players.get(j).getsPlayerName(),
									asteroids.get(i).getPositionX() + "", asteroids.get(i).getPositionY() + "");

							client.sendPacket(Settings._REQUEST_METEORGAME_METEOR_PLAYER_SIZE_UP + "",
									getnInitRoomNumber() + "", players.get(j).getsPlayerName(),
									players.get(j).getImageSizeX() + "", players.get(j).getImageSizeY() + "");
							break;
						}

					}
				}

				if (asteroids.size() <= 0 && isMeteorGameFinishCheck && isMeteorGameStartPrepareFinish) {
					client.sendPacket(Settings._REQUEST_METEORGAME_METEOR_GAME_FINISH + "", getnInitRoomNumber() + "",
							sNowMeteorGameWinner);
					isMeteorGameFinishCheck = false;
				}

			}

		};

		spriteAnimationTimer.start();

	}

	/**
	 * chcek unit bound on the main game scene
	 * 
	 * @param playerBound
	 */
	private void checkBound(GraphicsContextSprite playerBound) {
		if (playerBound.getPositionX() < playerBound.getImageSizeX() / 2)
			playerBound.setPositionX(playerBound.getImageSizeX() / 2);
		else if (playerBound.getPositionX() > Settings.nGameAsteroidSceneWidth - playerBound.getImageSizeX() / 2)
			playerBound.setPositionX(Settings.nGameAsteroidSceneWidth - playerBound.getImageSizeX() / 2);

		if (playerBound.getPositionY() < playerBound.getImageSizeY() / 2)
			playerBound.setPositionY(playerBound.getImageSizeY() / 2);
		else if (playerBound.getPositionY() > Settings.nGameAsteroidSceneHeight - playerBound.getImageSizeY() / 2)
			playerBound.setPositionY(Settings.nGameAsteroidSceneHeight - playerBound.getImageSizeY() / 2);
	}

	/**
	 * init drawing about TicTacToc
	 */
	private void drawShapesTictactoc() {
		Sprite board = new Sprite("total", 0, 0, 64, 64);
		Platform.runLater(() -> {
			for (int i = 0; i < Settings.nTicTacTocBlockWidth; i++)
				for (int j = 0; j < Settings.nTicTacTocBlockHeight; j++)
					drawImage(board, Settings.nTicTacTocImageWidth * i, Settings.nTicTacTocImageHeight * j,
							Settings.nTicTacTocImageWidth, Settings.nTicTacTocImageHeight);
		});

	}

	/**
	 * init drawing about catchMe
	 */
	private void drawShapesCatchMe() {
		Sprite board = new Sprite("total", 0, 0, 64, 64);
		Platform.runLater(() -> {
			for (int i = 0; i < Settings.nCatchMeBlockWidth; i++)
				for (int j = 0; j < Settings.nCatchMeBlockHeight; j++)
					drawImage(board, Settings.nCatchMeImageWidth * i, Settings.nCatchMeImageHeight * j,
							Settings.nCatchMeImageWidth, Settings.nCatchMeImageHeight);
		});

	}

	/**
	 * handle button release event for dynamic graphic game
	 * 
	 * @param e
	 */
	public void handleBtnKeyReleaseEvent(KeyEvent e) {
		if (!input.isEmpty())
			input.clear();
	}

	/**
	 * handle key event about sending message
	 * 
	 * @param e
	 */
	public void handleBtnKeyEvent(KeyEvent e) {
		switch (e.getCode()) {
		case ENTER:
			sendingMessageInTheRoom();
			break;

		case BACK_SPACE:
			break;

		case LEFT:
			meteorGameInputKeyManager(e);
			break;

		case RIGHT:
			meteorGameInputKeyManager(e);
			break;
		case UP:
			if (nCommandIndicatorPoisition != nCommandsContainerIndicator
					&& nCommandIndicatorPoisition > Settings.ERRORCODE)
				setServerCommandTextEdit();

			if (nCommandIndicatorPoisition != nCommandsContainerIndicator)
				nCommandIndicatorPoisition--;

			if (nCommandIndicatorPoisition < 0
					&& sCommandsContainer[Settings.nMaximumSizeOfCommandsContainer - 1] != null)
				nCommandIndicatorPoisition = Settings.nMaximumSizeOfCommandsContainer - 1;

			meteorGameInputKeyManager(e);

			break;
		case DOWN:
			if (nCommandIndicatorPoisition != _firstPoistion)
				nCommandIndicatorPoisition++;

			if (nCommandIndicatorPoisition >= Settings.nMaximumSizeOfCommandsContainer
					&& sCommandsContainer[Settings.ZEROINIT] != null)
				nCommandIndicatorPoisition = Settings.ZEROINIT;

			if (nCommandIndicatorPoisition != nCommandsContainerIndicator
					&& nCommandIndicatorPoisition > Settings.ERRORCODE)
				setServerCommandTextEdit();

			meteorGameInputKeyManager(e);

			break;

		default:
			e.consume();
			break;
		}
	}

	/**
	 * commend text saving container
	 */
	private void setServerCommandTextEdit() {
		Platform.runLater(() -> cheatingTextEdit.setText(sCommandsContainer[nCommandIndicatorPoisition]));
		Platform.runLater(() -> cheatingTextEdit.positionCaret(cheatingTextEdit.getLength()));

	}

	/**
	 * meteor game key event managing parts
	 * 
	 * @param e
	 */
	private void meteorGameInputKeyManager(KeyEvent e) {
		String code = e.getCode().toString();
		if (!input.contains(code))
			input.add(code);
	}

	/**
	 * handle the sending message in the game room
	 */
	private void sendingMessageInTheRoom() {
		String packet = cheatingTextEdit.getText();

		{
			sCommandsContainer[nCommandsContainerIndicator] = packet;
			nCommandIndicatorPoisition = nCommandsContainerIndicator;
			_firstPoistion = nCommandIndicatorPoisition;
			nCommandsContainerIndicator++;

			if (nCommandsContainerIndicator >= Settings.nMaximumSizeOfCommandsContainer)
				nCommandsContainerIndicator = Settings.ZEROINIT;
		}

		if (packet.length() > 0) {
			cheatingTextEdit.clear();

			client.sendPacket(Settings._REQUEST_ROOM_MEMEBER_MESSAGE + "", packet, getnInitRoomNumber() + "");
		}
	}

	/**
	 * handle the button about sending message
	 * 
	 * @param e
	 */
	public void handleBtnSendingMessage(ActionEvent e) {

		sendingMessageInTheRoom();
	}

	/**
	 * set the primary stage
	 * 
	 * @param primaryStage
	 */
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/**
	 * handle the start or stop button, this method check the lable's text and
	 * matching condition.
	 * 
	 * @param event
	 */
	public void handlebtnStartStop(ActionEvent event) {
		if (btnStartCancel.getText().equals("시작")) {
			client.sendPacket(Settings._REQUEST_START_THE_GAME + "", client.getClientName(), getnInitRoomNumber() + "",
					Settings.isGamePrepareStart + "");

		} else if (btnStartCancel.getText().equals("취소")) {
			client.sendPacket(Settings._REQUEST_START_THE_GAME + "", client.getClientName(), getnInitRoomNumber() + "",
					Settings.isGamePrepareStop + "");
		}
	}

	/**
	 * display the text on the cheatingTextArea
	 * 
	 * @param text
	 */
	private void displayText(String text) {
		cheatingTextArea.appendText(text + "\n");
	}

	/**
	 * handle the cancel button, if click this button then packet send the
	 * server
	 * 
	 * @param event
	 */
	public void handleBtnCancel(ActionEvent event) {
		client.sendPacket(Settings._REQUEST_OUT_OF_THE_ROOM + "", client.getClientName());
	}

	/**
	 * if client out the room successfully, scene change to waitingroom
	 */
	public void outOfTheRoom() {
		if (getnGameType() == Settings.nGameMeteorGame) {
			spriteAnimationTimer.stop();
			client.sendPacket(Settings._REQUEST_METEORGAME_OUT_OF_PLAYER + "", getnInitRoomNumber() + "",
					client.getClientName());

		} else if (getnGameType() == Settings.nGamePangPang) {
			spriteAnimationTimer.stop();
			soundPangPangBackground.stopMusic();
			client.sendPacket(Settings._REQUEST_PANGPANG_OUT_OF_PLAYER + "", getnInitRoomNumber() + "",
					client.getClientName());

		}
		Stage stage = (Stage) btnCancel.getScene().getWindow();

		Platform.runLater(() -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/room.fxml"));
				Parent loginScene = (Parent) loader.load();
				Scene scene = new Scene(loginScene);
				scene.getStylesheets().add(getClass().getResource("/Css/app.css").toString());
				WaitingRoomsManagerController waitingRoomsManagerController = loader.getController();
				waitingRoomsManagerController.setPrimaryStage(stage);
				stage.setScene(scene);
				stage.show();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	/**
	 * handle the set_ready protocol change the button text
	 * 
	 * @param packet
	 */
	public void gameSetReady(String[] packet) {
		if (true == Boolean.parseBoolean(packet[1]))
			Platform.runLater(() -> {
				btnStartCancel.setText("취소");
			});
		else
			Platform.runLater(() -> {
				btnStartCancel.setText("시작");
			});

		client.sendPacket(Settings._REQUEST_ANWER_ROOM_GAME_MESSAGE + "", getnInitRoomNumber() + "", packet[1]);
	}

	/**
	 * if the gameState is game Run. then this function is running for game
	 * 
	 * @param packet
	 */
	public void gameRoomSetReady(String[] packet) {

		if (Settings.isRoomGameStart == Boolean.parseBoolean(packet[1])) {
			setGameStart(Settings.isRoomGameStart);
			Platform.runLater(() -> {
				btnStartCancel.setText("게임 중");
				btnStartCancel.setDisable(true);

				if (getnGameType() == Settings.nGameTicTacToc) {
					for (int i = 1; i < this.nEnteredMember + 1; i++)
						ticTacTocMultipeColorboard[i - 1] = new Sprite("total", 64 * (i % 3), 64 * (i / 3), 64, 64);

					File file = new File("src/Asset/total.png");
					Image image = new Image(file.toURI().toString());

					if (isTicTacTocTwoColorMode == false) {
						imageGameMainView.setImage(image);
						int _tag = client.getClientUniqueGameTagNumber();
						_tag++;

						if (_tag >= this.nEnteredMember)
							_tag = 0;

						imageGameMainView.setViewport(new Rectangle2D(ticTacTocMultipeColorboard[_tag].getImageStartX(),
								ticTacTocMultipeColorboard[_tag].getImageStartY(),
								ticTacTocMultipeColorboard[_tag].getImageWidth(),
								ticTacTocMultipeColorboard[_tag].getImageHeight()));

					} else {
						imageGameMainView.setImage(image);
						imageGameMainView.setViewport(new Rectangle2D(0, 64, 64, 64));
					}
				}
			});

			if (Integer.parseInt(packet[2]) == client.getClientUniqueGameTagNumber()) {
				setPlayToken(true);
				client.sendPacket(Settings._REQUEST_TICTACTOC_TURN_PLAYER_NAME + "", getnInitRoomNumber() + "",
						client.getClientName());
			} else
				setPlayToken(false);

			if (getnGameType() == Settings.nGameCatchMe && isPlayToken()) {
				client.sendPacket(Settings._REQUEST_CATCHME_SELECT_ITEM + "");
			}

		} else if (Settings.isRoomGameStop == Boolean.parseBoolean(packet[1])) {
			setGameStart(Settings.isRoomGameStop);
			System.out.println("game stop");
		}

	}

	/**
	 * set the lbplayerTurn label. using current token position
	 * 
	 * @param packet
	 */
	public void setTurnPlayerName(String[] packet) {
		if (getnGameType() == Settings.nGamePangPang) {
			Platform.runLater(() -> {
				lbPlayerTurn.setText(nPangPangScore + "");
			});

			return;
		}

		if (!lbPlayerTurn.getText().equals(packet[1]))
			Platform.runLater(() -> {
				lbPlayerTurn.setText(packet[1]);
			});
	}

	/**
	 * handle the end of the game this method show the end of game state on the
	 * each client's textBoard
	 * 
	 * @param packet
	 */
	public void endOfTheGame(String[] packet) {
		this.setGameStart(false);

		if (Integer.parseInt(packet[0]) == Settings._ANSWER_GAME_END) {

			if (Integer.parseInt(packet[1]) == client.getClientUniqueGameTagNumber())
				Platform.runLater(() -> {
					displayText("[" + client.getClientName() + "] Win!!!");
				});
			else
				Platform.runLater(() -> {
					displayText("[" + client.getClientName() + "] Defeat!!");
				});
		} else if (Integer.parseInt(packet[0]) == Settings._ANSWER_GAME_END_REVERSE) {

			if (Integer.parseInt(packet[1]) == client.getClientUniqueGameTagNumber())
				Platform.runLater(() -> {
					displayText("[" + client.getClientName() + "] Defeat!!!");
				});
			else
				Platform.runLater(() -> {
					displayText("[" + client.getClientName() + "] Win!!");
				});
		}
	}

	/**
	 * set the playerTurn Name (first Time)
	 * 
	 * @param packet
	 */
	public void initTurnPlayerName(String packet) {
		if (getnGameType() == Settings.nGamePangPang) {
			Platform.runLater(() -> {
				lbPlayerTurn.setText(0 + "");
			});
			return;
		}

		Platform.runLater(() -> {
			lbPlayerTurn.setText(packet);
		});
	}

	/**
	 * set the TicTacToc Color mode, console
	 * 
	 * @param packet
	 */
	public void ticTacTocGameColorSetting(String[] packet) {
		boolean _check = Boolean.parseBoolean(packet[1]);

		isTicTacTocTwoColorMode = _check;

		if (_check)
			Platform.runLater(() -> displayText("Game Color Mode가 Binary 입니다."));
		else
			Platform.runLater(() -> displayText("Game Color Mode가 Color 입니다."));
	}

	/**
	 * set the TicTacToc AI mode, console
	 * 
	 * @param packet
	 */
	public void ticTacTocGameAISetting(String[] packet) {
		boolean _check = Boolean.parseBoolean(packet[1]);

		isTicTacTocTwoColorMode = _check;

		if (_check)
			Platform.runLater(() -> displayText("Game AI ATTACK Mode."));
		else
			Platform.runLater(() -> displayText("Game AI DEFENSE Mode."));
	}

	/**
	 * set the CatchMe user's Item
	 * 
	 * @param packet
	 */
	public void catchMeItemSetting(String[] packet) {
		int _catchmeItemNumber = Integer.parseInt(packet[1]);

		setnCatchmeItem(_catchmeItemNumber);

		Platform.runLater(() -> {
			File file = new File("src/Asset/buttons.png");
			Image image = new Image(file.toURI().toString());
			imageGameMainView.setImage(image);
			imageGameMainView.setViewport(new Rectangle2D(108 * _catchmeItemNumber, 93, 108, 93));
		});

	}

	/**
	 * set The CatchMe Game Possible play count
	 * 
	 * @param packet
	 */
	public void setCatchmePlayCount(String[] packet) {
		Platform.runLater(() -> displayText("Play count Set Number :" + packet[1]));
	}

	public void setMeteorGamePlayerPosition(String[] packet) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getsPlayerName().equals(packet[1]))
				players.get(i).setPosition(Double.parseDouble(packet[2]), Double.parseDouble(packet[3]));
		}

	}

	/**
	 * init pangpang game when first time
	 * 
	 * @param packet
	 */
	public void initPangPangGamePlayerGamePosition(String[] packet) {
		if (Settings.ERRORCODE != checkPlayerInThePangPang(packet[1]))
			return;

		PangPangPlayer player = new PangPangPlayer("/Asset/pangpang_charecter.png", 400, 0, 54, 57);
		player.setPosition(Double.parseDouble(packet[2]), Double.parseDouble(packet[3]));
		player.setImageSize(30, 30);
		player.setsPlayerName(packet[1]);
		player.setDirection(PangPangPlayer.UP);
		pangPangPlayers.add(player);

		for (int i = 0; i < pangPangPlayers.size(); i++) {
			if (pangPangPlayers.get(i).getsPlayerName().equals(client.getClientName())) {
				client.sendPacket(Settings._REQUEST_PANGPANG_REINIT_GAME_PLAY + "", getnInitRoomNumber() + "",
						packet[1], client.getClientName(), pangPangPlayers.get(i).getPositionX() + "",
						pangPangPlayers.get(i).getPositionY() + "");
				clientPangPangMainPlayer = pangPangPlayers.get(i);
			}

		}
	}

	/**
	 * init meteorgame when first time
	 * 
	 * @param packet
	 */
	public void initMeteorGamePlayerGamePosition(String[] packet) {
		if (Settings.ERRORCODE != checkPlayerInTheMeteorGame(packet[1]))
			return;

		Player player;

		player = initMeteorPlayerColor(packet[1]);
		player.setPosition(Double.parseDouble(packet[2]), Double.parseDouble(packet[3]));
		player.setImageSize(30, 30);
		player.setsPlayerName(packet[1]);
		players.add(player);

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getsPlayerName().equals(client.getClientName())) {
				client.sendPacket(Settings._REQUEST_METEORGAME_REINIT_GAME_PLAY + "", getnInitRoomNumber() + "",
						packet[1], client.getClientName(), players.get(i).getPositionX() + "",
						players.get(i).getPositionY() + "");
				clientMainPlayer = players.get(i);
			}

		}

	}

	/**
	 * init meteor game colors which is used for determining each player
	 * 
	 * @param packet
	 * @return
	 */
	private Player initMeteorPlayerColor(String packet) {
		Player player;
		if (client.getClientName().equals(packet))
			player = new Player("/Asset/box.png", 0, 0, 100, 100);
		else
			player = new Player("/Asset/box.png", 100, 0, 100, 100);
		return player;
	}

	/**
	 * check player stay in meteor game
	 * 
	 * @param name
	 * @return
	 */
	private int checkPlayerInTheMeteorGame(String name) {
		for (int i = 0; i < players.size(); i++)
			if (players.get(i).getsPlayerName().equals(name))
				return i;
		return Settings.ERRORCODE;
	}

	/**
	 * check player stay in pangpang game
	 * 
	 * @param name
	 * @return
	 */
	private int checkPlayerInThePangPang(String name) {
		for (int i = 0; i < pangPangPlayers.size(); i++)
			if (pangPangPlayers.get(i).getsPlayerName().equals(name))
				return i;
		return Settings.ERRORCODE;
	}

	/**
	 * re init the pangpang game for second time
	 * 
	 * @param packet
	 */
	public void initRePangPangGamePlayerGamePosition(String[] packet) {
		boolean isExisted = false;

		if (client.getClientName().equals(packet[1]))
			if (!client.getClientName().equals(packet[2])) {
				for (int i = 0; i < pangPangPlayers.size(); i++) {
					if (pangPangPlayers.get(i).getsPlayerName().equals(packet[2])) {
						isExisted = true;
						break;
					}
				}

				if (isExisted == false) {
					PangPangPlayer player = new PangPangPlayer("/Asset/pangpang_charecter.png", 400, 0, 54, 57);
					player.setPosition(Double.parseDouble(packet[3]), Double.parseDouble(packet[4]));
					player.setDirection(PangPangPlayer.UP);
					player.setImageSize(30, 30);
					player.setsPlayerName(packet[2]);
					pangPangPlayers.add(player);
				}
			}

	}

	/**
	 * re init meteor game for second time
	 * 
	 * @param packet
	 */
	public void initReMeteorGamePlayerGamePosition(String[] packet) {
		boolean isExisted = false;

		if (client.getClientName().equals(packet[1]))
			if (!client.getClientName().equals(packet[2])) {
				for (int i = 0; i < players.size(); i++) {
					if (players.get(i).getsPlayerName().equals(packet[2])) {
						isExisted = true;
						break;
					}
				}
				Player player;

				if (isExisted == false) {

					player = initMeteorPlayerColor(packet[2]);

					player.setPosition(Double.parseDouble(packet[3]), Double.parseDouble(packet[4]));
					player.setImageSize(30, 30);
					player.setsPlayerName(packet[2]);
					players.add(player);
				}
			}

	}

	/**
	 * process player out of room event in meteorgame
	 * 
	 * @param packet
	 */
	public void outOfPlayerInMeteorGame(String[] packet) {
		players.remove(checkPlayerInTheMeteorGame(packet[1]));
	}

	/**
	 * process player out of room event in pangpang game
	 * 
	 * @param packet
	 */
	public void outOfPlayerInPangPang(String[] packet) {
		pangPangPlayers.remove(checkPlayerInThePangPang(packet[1]));
	}

	/**
	 * init meteor game when start the meteor game
	 * 
	 * @param packet
	 */
	public void initMeteorGameWhenStartGame(String[] packet) {
		Player asteroid = new Player("/Asset/box.png", 0, 100, 100, 100);
		asteroid.setPosition(Double.parseDouble(packet[1]), Double.parseDouble(packet[2]));
		asteroid.setImageSize(5, 5);
		asteroid.setsPlayerName("asteroid");
		asteroids.add(asteroid);
	}

	/**
	 * start the pangpang game when finish preparing game
	 * 
	 * @param packet
	 */
	public void StartPrepareCompletePangPang(String[] packet) {
		isPangPangStartPrepareFinish = Boolean.parseBoolean(packet[1]);
		soundPangPangBackground.repeatStartMusic();
		Platform.runLater(() -> displayText("Game Start!!"));
	}

	/**
	 * init pangapng enemy
	 */
	public void initPangPangBubbles() {
		for (int i = 0; i < Settings.nPangPangEnemyHeight; i++)
			for (int j = 0; j < Settings.nPangPangEnemyWidth; j++) {
				PangPangEnemy bubble = new PangPangEnemy("/Asset/box.png", 0, 100, 100, 100);
				bubble.setImageSize(20, 20);
				bubble.setsPlayerName("bubble" + (i * Settings.nPangPangEnemyWidth + j));
				bubbles.add(bubble);
			}

	}

	/**
	 * start the meteor game when finish preparing game
	 * 
	 * @param packet
	 */
	public void StartPrepareCompleteMeteorGame(String[] packet) {
		isMeteorGameStartPrepareFinish = Boolean.parseBoolean(packet[1]);
		Platform.runLater(() -> displayText("Game Start!!"));
	}

	/**
	 * update meteor game player position
	 * 
	 * @param packet
	 */
	public void updateMeteorGamePlayerPosition(String[] packet) {
		if (client.getClientName().equals(packet[1]))
			return;

		for (int i = 0; i < players.size(); i++)
			if (players.get(i).getsPlayerName().equals(packet[1])) {
				players.get(i).setPosition(Double.parseDouble(packet[2]), Double.parseDouble(packet[3]));
			}
	}

	/**
	 * update pangpang player position
	 * 
	 * @param packet
	 */
	public void updatePangPangPlayerPosition(String[] packet) {

		for (int i = 0; i < pangPangPlayers.size(); i++)
			if (pangPangPlayers.get(i).getsPlayerName().equals(packet[1])) {
				pangPangPlayers.get(i).setDirection(Integer.parseInt(packet[2]));
			}
	}

	/**
	 * finish meteor game event
	 */
	public void setFinishMeteorGame() {
		this.setGameStart(false);

		Platform.runLater(() -> {
			displayText("[" + sNowMeteorGameWinner + "] Win!!!");
		});

	};

	/**
	 * delete astroid in meteor game
	 * 
	 * @param packet
	 */
	public void deleteMeteorGameMeteor(String[] packet) {

		if (isMeteorGameStartPrepareFinish == false)
			return;

		for (int i = 0; i < asteroids.size(); i++)
			if (asteroids.get(i).getPositionX() == Double.parseDouble(packet[2])
					&& asteroids.get(i).getPositionY() == Double.parseDouble(packet[3])) {
				asteroids.remove(i);
				System.out.println("error check : " + packet[1] + " , " + client.getClientName());
				if (client.getClientName().equals(packet[1])) {
					nMeteorGameDestroyCount++;
					Platform.runLater(() -> {
						lbPlayerTurnBlockText.setText("count : " + nMeteorGameDestroyCount);
					});

					Platform.runLater(() -> {
						lbPlayerTurn.setText(packet[4]);
					});

				}
			}

		sNowMeteorGameWinner = packet[4];

		Platform.runLater(() -> {
			lbPlayerTurn.setText(packet[4]);
		});
	}

	/**
	 * when end of the catchMe game, this method is called for redrawing image
	 * about moving token
	 * 
	 * @param packet
	 */
	public void catchmeReDrawMap(String[] packet) {
		int _x = Integer.parseInt(packet[1]);
		int _y = Integer.parseInt(packet[2]);
		Platform.runLater(() -> {
			for (int i = 0; i < anchorPane.getChildren().size(); i++) {
				if (anchorPane.getChildren().get(i).getLayoutX() == _x * Settings.nCatchMeImageWidth
						&& anchorPane.getChildren().get(i).getLayoutY() == _y * Settings.nCatchMeImageHeight) {
					File file = new File("src/Asset/start_moon.png");
					Image image = new Image(file.toURI().toString());
					imageGameMainView.setImage(image);
					((ImageView) anchorPane.getChildren().get(i)).setImage(image);
					((ImageView) anchorPane.getChildren().get(i)).setViewport(new Rectangle2D(67, 0, 67, 67));

					imageGameMainView.setImage(imageLogo);
					imageGameMainView.setViewport(new Rectangle2D(0, 0, 32, 32));
				}
			}
		});
	}

	/**
	 * set the CatchMe Click number on the label(lbCatchmePlayCount)
	 * 
	 * @param packet
	 */
	public void catchmeClickPlayNumberSet(String[] packet) {
		setnPossiblePlayNumber(Integer.parseInt(packet[1]));
		Platform.runLater(() -> {
			lbCatchmePlayCount.setText(getnPossiblePlayNumber() + "");
		});
	}

	/**
	 * handle the catchMeplay
	 * 
	 * @param packet
	 */
	public void catchmePlay(String[] packet) {
		int x = Integer.parseInt(packet[2]);
		int y = Integer.parseInt(packet[3]);

		if (Boolean.parseBoolean(packet[4]) == true) {
			int _flag;

			_flag = Integer.parseInt(packet[5]);

			_flag--;

			if (_flag < 0)
				_flag = nEnteredMember - 1;

			int _catchmeItemNumber = Integer.parseInt(packet[6]);
			int _setTheItemview = Integer.parseInt(packet[7]);

			if (_flag == client.getClientUniqueGameTagNumber()) {

				Sprite board = new Sprite("buttons", 108 * _catchmeItemNumber, 93, 108, 93);
				Platform.runLater(() -> {
					for (int i = 0; i < anchorPane.getChildren().size(); i++) {
						if (anchorPane.getChildren().get(i).getLayoutX() == x * Settings.nCatchMeImageWidth
								&& anchorPane.getChildren().get(i).getLayoutY() == y * Settings.nCatchMeImageHeight)
							anchorPane.getChildren().remove(i);
					}
					drawImage(board, Settings.nCatchMeImageWidth * x, Settings.nCatchMeImageHeight * y,
							Settings.nCatchMeImageWidth, Settings.nCatchMeImageHeight);

				});

				client.sendPacket(Settings._REQUEST_TICTACTOC_TURN_PLAYER_NAME + "", getnInitRoomNumber() + "",
						client.getClientName());

				if (Integer.parseInt(packet[8]) != Settings.ERRORCODE) {
					setnPossiblePlayNumber(Integer.parseInt(packet[8]));
					Platform.runLater(() -> {
						lbCatchmePlayCount.setText(getnPossiblePlayNumber() + "");
					});
				} else
					System.out.println("error");

				if (Integer.parseInt(packet[5]) == client.getClientUniqueGameTagNumber()) {
					setPlayToken(true);

					Platform.runLater(() -> {
						setnCatchmeItem(_setTheItemview);
						File file = new File("src/Asset/buttons.png");
						Image image = new Image(file.toURI().toString());
						imageGameMainView.setImage(image);
						imageGameMainView.setViewport(new Rectangle2D(108 * _setTheItemview, 93, 108, 93));
					});

				} else {

					setnCatchmeItem(Settings.ERRORCODE);

					setPlayToken(false);
				}

			} else {
				Sprite board = new Sprite("start_moon", 0, 0, 67, 67);
				Platform.runLater(() -> {
					for (int i = 0; i < anchorPane.getChildren().size(); i++) {
						if (anchorPane.getChildren().get(i).getLayoutX() == x * Settings.nCatchMeImageWidth
								&& anchorPane.getChildren().get(i).getLayoutY() == y * Settings.nCatchMeImageHeight)
							anchorPane.getChildren().remove(i);
					}
					drawImage(board, Settings.nCatchMeImageWidth * x, Settings.nCatchMeImageHeight * y,
							Settings.nCatchMeImageWidth, Settings.nCatchMeImageHeight);

				});

				if (Integer.parseInt(packet[5]) == client.getClientUniqueGameTagNumber()) {
					setPlayToken(true);

					Platform.runLater(() -> {
						setnCatchmeItem(_setTheItemview);
						File file = new File("src/Asset/buttons.png");
						Image image = new Image(file.toURI().toString());
						imageGameMainView.setImage(image);
						imageGameMainView.setViewport(new Rectangle2D(108 * _setTheItemview, 93, 108, 93));
					});

				} else {
					setnCatchmeItem(Settings.ERRORCODE);

					setPlayToken(false);
				}
			}
		} else {
			if (Integer.parseInt(packet[5]) == client.getClientUniqueGameTagNumber()) {
			}
		}

		if (Boolean.parseBoolean(packet[4]) == false) {
			setPlayToken(true);
			handlePopup("이미 패가 놓여진 공간 입니다.");
		}
	}

	/**
	 * handle the TicTacTocplay Game
	 * 
	 * @param packet
	 */
	public void ticTactocPlay(String[] packet) {

		int x = Integer.parseInt(packet[2]);
		int y = Integer.parseInt(packet[3]);

		if (Boolean.parseBoolean(packet[4]) == true) {
			int _flag;

			_flag = Integer.parseInt(packet[5]);

			if (isTicTacTocTwoColorMode) {
				_flag--;

				if (_flag < 0)
					_flag = nEnteredMember - 1;
			}

			if (_flag == client.getClientUniqueGameTagNumber()) {
				Sprite board = new Sprite("total", 0, 64, 64, 64);
				Platform.runLater(() -> {
					for (int i = 0; i < anchorPane.getChildren().size(); i++) {
						if (anchorPane.getChildren().get(i).getLayoutX() == x * Settings.nTicTacTocImageWidth
								&& anchorPane.getChildren().get(i).getLayoutY() == y * Settings.nTicTacTocImageHeight)
							anchorPane.getChildren().remove(i);
					}
					if (isTicTacTocTwoColorMode)
						drawImage(board, Settings.nTicTacTocImageWidth * x, Settings.nTicTacTocImageHeight * y,
								Settings.nTicTacTocImageWidth, Settings.nTicTacTocImageHeight);
					else
						drawImage(ticTacTocMultipeColorboard[Integer.parseInt(packet[5])],
								Settings.nTicTacTocImageWidth * x, Settings.nTicTacTocImageHeight * y,
								Settings.nTicTacTocImageWidth, Settings.nTicTacTocImageHeight);

				});

				client.sendPacket(Settings._REQUEST_TICTACTOC_TURN_PLAYER_NAME + "", getnInitRoomNumber() + "",
						client.getClientName());

				if (Integer.parseInt(packet[5]) == client.getClientUniqueGameTagNumber())
					setPlayToken(true);

			} else {
				Sprite board = new Sprite("total", 64, 0, 64, 64);
				Sprite boardSecond = new Sprite("total", 0, 64, 64, 64);
				Platform.runLater(() -> {
					for (int i = 0; i < anchorPane.getChildren().size(); i++) {
						if (anchorPane.getChildren().get(i).getLayoutX() == x * Settings.nTicTacTocImageWidth
								&& anchorPane.getChildren().get(i).getLayoutY() == y * Settings.nTicTacTocImageHeight)
							anchorPane.getChildren().remove(i);
					}
					if (isTicTacTocTwoColorMode) {
						drawImage(board, Settings.nTicTacTocImageWidth * x, Settings.nTicTacTocImageHeight * y,
								Settings.nTicTacTocImageWidth, Settings.nTicTacTocImageHeight);
					} else {
						if (nEnteredMember > 1)
							drawImage(ticTacTocMultipeColorboard[Integer.parseInt(packet[5])],
									Settings.nTicTacTocImageWidth * x, Settings.nTicTacTocImageHeight * y,
									Settings.nTicTacTocImageWidth, Settings.nTicTacTocImageHeight);
						else
							drawImage(boardSecond, Settings.nTicTacTocImageWidth * x,
									Settings.nTicTacTocImageHeight * y, Settings.nTicTacTocImageWidth,
									Settings.nTicTacTocImageHeight);
					}
				});

				setPlayToken(false);

				if (isTicTacTocTwoColorMode && nEnteredMember > 1
						&& Integer.parseInt(packet[5]) == client.getClientUniqueGameTagNumber())
					setPlayToken(true);
			}
		} else {
			if (Integer.parseInt(packet[5]) == client.getClientUniqueGameTagNumber()) {
			}
		}

		if (Boolean.parseBoolean(packet[4]) == false) {
			setPlayToken(true);
			handlePopup("이미 패가 놓여진 공간 입니다.");
		}
	}

	/**
	 * popUp windows
	 * 
	 * @param text
	 */
	public void handlePopup(String text) {
		Platform.runLater(() -> {

			try {
				Popup popup = new Popup();

				Parent parent;

				parent = FXMLLoader.load(getClass().getResource("/Fxml/popup.fxml"));
				ImageView imageView = (ImageView) parent.lookup("#imgMessage");
				imageView.setImage(new Image(getClass().getResource("/Css/dialog-info.png").toString()));
				imageView.setOnMouseClicked(event -> popup.hide());
				Label lblMessage = (Label) parent.lookup("#lblMessage");
				lblMessage.setText(text);

				popup.setX(primaryStage.getX());
				popup.setY(primaryStage.getY());

				popup.getContent().add(parent);
				popup.setAutoHide(true);
				popup.show(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	}

	/**
	 * processing pangapng enemy position
	 */
	public void pangPangEnemyPositionProcess() {
		isPangPangEnemyStackRunning = true;
		if (sPangPangEnemyStack != null) {
			String sPositionSet[] = sPangPangEnemyStack.split(Settings.sPangPangPositionInformationWordToken);

			for (int i = 1; i < sPositionSet.length; i++) {
				String sSubPosition[] = sPositionSet[i].split(Settings.sPangPangPositionCoordinationToken);

				for (int j = 0; j < bubbles.size(); j++)
					if (isPangPangStartPrepareFinish && bubbles.get(j).getsPlayerName().equals(sSubPosition[0])) {
						bubbles.get(j).setPosition(Double.parseDouble(sSubPosition[1]),
								Double.parseDouble(sSubPosition[2]));
						break;
					}
			}

		}
		isPangPangEnemyStackRunning = false;
	}

	/**
	 * if player life is zero or under zero. then this method is called for
	 * deleting the player
	 * 
	 * @param packet
	 */
	public void pangpangPlayerRemove(String[] packet) {
		for (int i = 0; i < pangPangPlayers.size(); i++)
			if (pangPangPlayers.get(i).getsPlayerName().equals(packet[1]))
				pangPangPlayers.remove(i);
	}

	/**
	 * pangapng enemy delete when pangapng enemy's life under zero
	 * 
	 * @param packet
	 */
	public void pangpangEnemyRemove(String[] packet) {
		for (int i = 0; i < bubbles.size(); i++) {
			if (bubbles.get(i).getsPlayerName().equals(packet[1])) {
				Random rnd = new Random();

				for (int j = 0; j < rnd.nextInt(8); j++) {

					Explosion_Effect temp = new Explosion_Effect("/Asset/ball.png", 60 * rnd.nextInt(5), 0, 59, 58,
							rnd.nextInt(16));
					temp.setImageSize(10, 10);
					temp.setPosition(bubbles.get(i).getPositionX(), bubbles.get(i).getPositionY());

					pangpang_bubbles_effects.add(temp);
				}
				bubbles.remove(i);

			}
		}
	}

	/**
	 * update pangpang missile
	 * 
	 * @param packet
	 */
	public void pangpangMissileUpdate(String[] packet) {

		for (int i = 0; i < pangPangPlayers.size(); i++)
			if (pangPangPlayers.get(i).getsPlayerName().equals(packet[1])) {
				Enemy_Missile missile = new Enemy_Missile("/Asset/box.png", 0, 0, 100, 100,
						pangPangPlayers.get(i).getPositionX(), pangPangPlayers.get(i).getPositionY(), 0, packet[2]);
				missile.setImageSize(Enemy_Missile.BUBLLE_MISSILE_WIDTH, Enemy_Missile.BUBLLE_MISSILE_HEIHGT);

				player_Missiles.add(missile);
				break;
			}

	}

	/**
	 * update enemy missile
	 * 
	 * @param packet
	 */
	public void enemyMissileUpdate(String[] packet) {

		Enemy_Missile missile = new Enemy_Missile("/Asset/box.png", 100, 0, 100, 100, Double.parseDouble(packet[1]),
				Double.parseDouble(packet[2]), Integer.parseInt(packet[3]), packet[4]);
		missile.setImageSize(Enemy_Missile.BUBLLE_MISSILE_WIDTH, Enemy_Missile.BUBLLE_MISSILE_HEIHGT);

		bubbles_missiles.add(missile);

	}

	// sending event
	private String temp;

	/**
	 * pangapng receive enemy init event
	 */
	public void pangpangReceiveEnemyInit() {
		String sPositionSet[] = temp.split(Settings.sPangPangPositionInformationWordToken);

		for (int i = 1; i < sPositionSet.length; i++) {
			String sSubPosition[] = sPositionSet[i].split(Settings.sPangPangPositionCoordinationToken);

			for (int j = 0; j < bubbles.size(); j++)
				if (bubbles.get(j).getsPlayerName().equals(sSubPosition[0])) {
					bubbles.get(j).setImageTypeNumber(Integer.parseInt(sSubPosition[1]));
					if (bubbles.get(j).getImageTypeNumber() == 0)
						bubbles.get(j).reInitImage("/Asset/ball.png", 0, 0, 59, 58);
					else if (bubbles.get(j).getImageTypeNumber() == 1)
						bubbles.get(j).reInitImage("/Asset/ball.png", 60, 0, 59, 58);
					else if (bubbles.get(j).getImageTypeNumber() == 2)
						bubbles.get(j).reInitImage("/Asset/ball.png", 120, 0, 59, 58);
					else if (bubbles.get(j).getImageTypeNumber() == 3)
						bubbles.get(j).reInitImage("/Asset/ball.png", 180, 0, 59, 58);
					else if (bubbles.get(j).getImageTypeNumber() == 4)
						bubbles.get(j).reInitImage("/Asset/ball.png", 240, 0, 59, 58);
					else
						bubbles.get(j).reInitImage("/Asset/ball.png", 300, 0, 59, 58);
					bubbles.get(j).setImageSize(20, 20);
					bubbles.get(j).setDeath(Boolean.parseBoolean(sSubPosition[2]));
					break;
				}
		}
	}

	/**
	 * pangpang enemy init
	 * 
	 * @param packet
	 */
	public void pangpangEnemyInit(String[] packet) {

		temp = packet[1];
		isInit = true;
	}

	/**
	 * pangpang enemy position update
	 * 
	 * @param packet
	 */
	public void pangpangEnemyPositionUpdate(String[] packet) {
		// enemy Unit initialization part.
		if (false == isPangPangEnemyStackRunning)
			sPangPangEnemyStack = packet[1];
	}

	/**
	 * meteor game player who destroy asteroids increases size
	 * 
	 * @param packet
	 */
	public void meteorGamePlayerSizeUP(String[] packet) {
		for (int i = 0; i < players.size(); i++)
			if (players.get(i).getsPlayerName().equals(packet[1])) {
				players.get(i).setImageSizeX(Double.parseDouble(packet[2]));
				players.get(i).setImageSizeY(Double.parseDouble(packet[3]));
			}

	}

	/**
	 * get possible Play number (catchMe) integer
	 * 
	 * @return
	 */
	public int getnPossiblePlayNumber() {
		return nPossiblePlayNumber;
	}

	/**
	 * set possible play number (catchMe)
	 * 
	 * @param nPossiblePlayNumber
	 */
	public void setnPossiblePlayNumber(int nPossiblePlayNumber) {
		this.nPossiblePlayNumber = nPossiblePlayNumber;
	}

	/**
	 * set The Room Message on the cheatingTextArea
	 * 
	 * @param packet
	 */
	public void setTheRoomMessage(String[] packet) {
		Platform.runLater(() -> displayText("[" + packet[2] + "] " + packet[1]));
	}

	/**
	 * if new member enter the room, server notify that new memeber enter the
	 * this game room
	 * 
	 * @param packet
	 */
	public void notificationNewRoomMemeberMessage(String[] packet) {
		Platform.runLater(() -> displayText("[" + packet[1] + "] 님이 입장하셨습니다."));
	}

	/**
	 * get room name string
	 * 
	 * @return
	 */
	public String getsRoomName() {

		return sRoomName;
	}

	/**
	 * set room name( init time show the room name)
	 * 
	 * @param sRoomName
	 */
	public void setsRoomName(String sRoomName) {
		this.sRoomName = sRoomName;
		lbRoomName.setText(getsRoomName());
		displayText("[" + getsRoomName() + "]" + "에 입장하셨습니다.");
		client.sendPacket(Settings._REQUEST_NEW_ROOM_MEMEBER_NOTIFICATION + "", getnInitRoomNumber() + "");
	}

	/**
	 * get total Client Number integer
	 * 
	 * @return
	 */
	public int getnToTalClient() {
		return nToTalClient;
	}

	/**
	 * set total Client Number
	 * 
	 * @param nToTalClient
	 */
	public void setnToTalClient(int nToTalClient) {
		this.nToTalClient = nToTalClient;
		this.nEnteredMember = 1;
		lbMaximumMember.setText("" + getnToTalClient() + "/" + nEnteredMember);
		client.sendPacket(Settings._REQUEST_SET_GAME_PLAYER_UNIQUE_TAG_NUMBER + "");
	}

	/**
	 * get Game Type integer
	 * 
	 * @return
	 */
	public int getnGameType() {
		return nGameType;
	}

	/**
	 * set the Game type and draw the game iamge
	 * 
	 * @param nGameType
	 */
	public void setnGameType(int nGameType) {
		this.nGameType = nGameType;
		if (getnGameType() == Settings.nGameCatchMe) {
			lbGameStyle.setText(Settings.sGameStringStyleCatchMe);
			drawShapesCatchMe();

			lbCatchmePlayCount.setVisible(true);
			lbPlayerTurn.setVisible(true);

		} else if (getnGameType() == Settings.nGameTicTacToc) {
			lbGameStyle.setText(Settings.sGameStringStyleTicTacToc);
			drawShapesTictactoc();

			lbPlayerTurn.setVisible(true);
		} else if (getnGameType() == Settings.nGameMeteorGame) {
			lbGameStyle.setText(Settings.sGameStringStyleMeteorGame);
			lbPlayerTurnText.setText("winner");
			lbPlayerTurn.setVisible(true);
			lbPlayerTurnBlockText.setText("count : " + nMeteorGameDestroyCount);
			imageGameMainView.setVisible(false);
			drawSpriteImageViewMeteor();
		} else if (getnGameType() == Settings.nGamePangPang) {
			lbGameStyle.setText(Settings.sGameStringStylePangPang);
			drawSpriteImageViewPangPang();
			lbPlayerTurnText.setVisible(true);
			lbPlayerTurnText.setText("Score");
			lbPlayerTurnText.setLayoutY(210.0);
			imageGameMainView.setVisible(false);
			lbPlayerTurnBlockText.setVisible(false);
			lbPlayerTurn.setLayoutY(230.0);
			lbPlayerTurn.setVisible(true);

		}

		client.sendPacket(Settings._REQUEST_GAME_ROOM_MEMEBER_NUMBER + "", getsRoomName() + "");
	}

	/**
	 * set the entered room member number
	 * 
	 * @param packet
	 */
	public void setTheGameRoomMemberNumber(String[] packet) {
		Platform.runLater(() -> {
			String _string = getnToTalClient() + "";
			this.nEnteredMember = Integer.parseInt(packet[1]);
			_string += "/" + nEnteredMember;
			lbMaximumMember.setText(_string);
		});
	}

	/**
	 * check the max message length
	 * 
	 * @param max_Lengh
	 * @return
	 */
	public EventHandler<KeyEvent> message_text_Validation(final Integer max_Lengh) {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {

				TextField txt_TextField = (TextField) e.getSource();
				if (txt_TextField.getText().length() >= max_Lengh) {
					e.consume();
				}

			}
		};
	}

	/**
	 * get the game start state boolean
	 * 
	 * @return
	 */
	public boolean isGameStart() {
		return isGameStart;
	}

	/**
	 * set the game state state boolean
	 * 
	 * @param isGameStart
	 */
	public void setGameStart(boolean isGameStart) {
		this.isGameStart = isGameStart;
	}

	/**
	 * get the game token boolean
	 * 
	 * @return
	 */
	public boolean isPlayToken() {
		return isPlayToken;
	}

	/**
	 * set the game token
	 * 
	 * @param isPlayToken
	 */
	public void setPlayToken(boolean isPlayToken) {
		this.isPlayToken = isPlayToken;
	}

	/**
	 * get the catchMe item number
	 * 
	 * @return
	 */
	public int getnCatchmeItem() {
		return nCatchmeItem;
	}

	/**
	 * set the CatchMe Item number
	 * 
	 * @param nCatchmeItem
	 */
	public void setnCatchmeItem(int nCatchmeItem) {
		this.nCatchmeItem = nCatchmeItem;
	}

	/**
	 * get init room number for accelerating processing speed
	 * 
	 * @return
	 */
	public long getnInitRoomNumber() {
		return nInitRoomNumber;
	}

	/**
	 * set init room number for accelerating processing speed
	 * 
	 * @param nInitRoomNumber
	 */
	public void setnInitRoomNumber(long nInitRoomNumber) {
		this.nInitRoomNumber = nInitRoomNumber;
	}

}
