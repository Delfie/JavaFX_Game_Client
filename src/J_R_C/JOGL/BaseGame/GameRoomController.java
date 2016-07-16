
package J_R_C.JOGL.BaseGame;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
 * @author KJW finish at 2016/ 02/ 15
 * @version 1.0.0v
 * @description this class Manage The all the game window's state
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class GameRoomController implements Initializable {

    private final int SENDINGMESSAGEMAXLENGTH = 70;

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

    private AnimationTimer spriteAnimationTimer;

    private ArrayList<String> input = new ArrayList<String>();

    private ArrayList<Player> players = new ArrayList<Player>();

    private ArrayList<Player> asteroids = new ArrayList<Player>();

    private Player clientMainPlayer;

    private double meteriorGamePlayerPositionX;

    private double meteriorGamePlayerPositionY;

    private boolean isMeteorGameStartPrepareFinish;

    private int nMeteorGameDestroyCount;

    // = new GraphicsContextSprite("box.png", 100, 100);

    /*
     * (non-Javadoc)
     * @see javafx.fxml.Initializable#initialize(java.net.URL,
     * java.util.ResourceBundle) init the all state about games
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        isMeteorGameStartPrepareFinish = false;
        meteriorGamePlayerPositionX = 0;
        meteriorGamePlayerPositionY = 0;
        nMeteorGameDestroyCount = 0;
        this.setGameStart(false);
        lbCatchmePlayCount.setVisible(false);
        lbPlayerTurn.setVisible(false);

        // game play board size
        anchorPane.setMaxSize(GameViewMaximumWidth, GameViewMaximumHeight);
        // register click event listener
        anchorPane.setOnMouseClicked(e -> gameWindowsClickedEvent(e));
        // register click event listener
        btnCancel.setOnAction(e -> handleBtnCancel(e));
        btnStartCancel.setOnAction(e -> handlebtnStartStop(e));

        mainPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if (!input.contains(code))
                    input.add(code);
            }
        });

        mainPane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                input.remove(code);
            }
        });

        client = LoginController.getClient();
        client.setGameRoomController(this);

        lbPlayerTurnText.setStyle("-fx-font: 12 arial;");

        // set the variable about cheatingTextArea
        cheatingTextEdit.addEventFilter(KeyEvent.KEY_TYPED,
                message_text_Validation(SENDINGMESSAGEMAXLENGTH));
        cheatingTextArea.setEditable(false);
        cheatingTextArea
                .setStyle("-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color;-fx-background-insets: 0, 1, 2;-fx-background-radius: 5, 4, 3;");

        btnSendMessage.setOnAction(e -> handleBtnSendingMessage(e));
        cheatingTextEdit.setOnKeyPressed(e -> handleBtnKeyEvent(e));
        cheatingTextArea.requestFocus();

        isTicTacTocTwoColorMode = false;
        ticTacTocMultipeColorboard = new Sprite[8];

        // set the default image about imageGameMainView
        File file = new File("src/Asset/img_pangpang_32.png");
        imageLogo = new Image(file.toURI().toString());
        imageGameMainView.setImage(imageLogo);
        nCatchmeItem = -1;

        // Game updatePart and all time check server condition and image state
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        /*
                         * if (cheatingTextEdit.getText().length() > 29) {
                         * Platform.runLater(() ->
                         * handlePopup("25글자가 한계 입니다."));
                         * cheatingTextEdit.setText(cheatingTextEdit.getText(0,
                         * 29)); }
                         */

                        if (client.getIsServerConnected() == ServerClient.SERVERCONNECTIONFAIL) {
                            terminate();
                            handlePopup("서버와의 연결이 끊겼습니다.");
                            scheduler.shutdown();

                        }

                        if (getnGameType() == Settings.nGameCatchMe
                                && Settings.ERRORCODE == getnCatchmeItem()) {
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

        Stage stage = (Stage)primaryStage.getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/root.fxml"));
            Parent loginScene = (Parent)loader.load();
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
                anchorPane
                        .getChildren()
                        .get(i)
                        .addEventHandler(MouseEvent.MOUSE_CLICKED,
                                e -> tictactocClieckEvent(e, checkX, checkY));
            }
        }

        if (isGameStart() && getnGameType() == Settings.nGameCatchMe && isPlayToken() == true) {

            for (int i = 0; i < anchorPane.getChildren().size(); i++) {
                final double checkX = anchorPane.getChildren().get(i).getLayoutX();
                final double checkY = anchorPane.getChildren().get(i).getLayoutY();
                anchorPane
                        .getChildren()
                        .get(i)
                        .addEventHandler(MouseEvent.MOUSE_CLICKED,
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
            int _x = (int)x / Settings.nCatchMeImageWidth;
            int _y = (int)y / Settings.nCatchMeImageHeight;

            client.sendPacket(5, Settings._REQUEST_CATCHME_STONE_EVENT + "", getsRoomName(), _x
                    + "", _y + "", getnCatchmeItem() + "");

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
            int _x = (int)x / Settings.nTicTacTocImageWidth;
            int _y = (int)y / Settings.nTicTacTocImageHeight;

            client.sendPacket(4, Settings._REQUEST_TICTACTOC_STONE_EVENT + "", getsRoomName(), _x
                    + "", _y + "");

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

        imageview
                .setViewport(new Rectangle2D(_imageStartX, _imageStartY, _imageWidth, _imageHeight));

        imageview.setFitWidth(newWidth);
        imageview.setFitHeight(newHeight);

        anchorPane.getChildren().add(imageview);
    }

    private void drawSpriteImageView() {
        client.sendPacket(3, Settings._REQUEST_METEORGAME_INIT_GAME_PLAY + "", getsRoomName(),
                client.getClientName());

        // Mapping 사영 시키는 함수 만들것.
        Canvas canvas = new Canvas(360, 280);

        anchorPane.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        /*
         * player.setPosition(160, 240); player.setImageSize(30, 30);
         */

        anchorPane.getScene().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                client.sendPacket(4, Settings._REQUEST_METEORGAME_SET_CLIECK_EVENT + "",
                        getsRoomName(), e.getX() - 100 + "", e.getY() - 14 + "");

            }
        });

        GraphicsContextSprite background = new GraphicsContextSprite("background.png", 320, 480);

        spriteAnimationTimer = new AnimationTimer() {

            Long lastNanoTime = new Long(System.nanoTime());

            public void handle(long currentNanoTime) {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;

                // game logic

                if (input.contains("LEFT"))
                    clientMainPlayer.addVelocity(-3, 0);
                if (input.contains("RIGHT"))
                    clientMainPlayer.addVelocity(3, 0);
                if (input.contains("UP"))
                    clientMainPlayer.addVelocity(0, -3);
                if (input.contains("DOWN"))
                    clientMainPlayer.addVelocity(0, 3);

                // render

                gc.clearRect(0, 0, Settings.fSPRITEGAMEWIDTH, Settings.fSPRITEGAMEHEIGHT);
                background.render(gc, Settings.nGameAsteroidSceneWidth / 2,
                        Settings.nGameAsteroidSceneHeight / 2, Settings.nGameAsteroidSceneWidth,
                        Settings.nGameAsteroidSceneHeight);
                if (clientMainPlayer != null) {
                    clientMainPlayer.update(elapsedTime);

                    if (Math.abs(meteriorGamePlayerPositionX - clientMainPlayer.getPositionX()) > 0.5
                            || Math.abs(meteriorGamePlayerPositionY
                                    - clientMainPlayer.getPositionY()) > 0.5) {
                        client.sendPacket(5, Settings._REQUEST_METEORGAME_PLAYER_MOVING + "",
                                getsRoomName(), client.getClientName(),
                                clientMainPlayer.getPositionX() + "",
                                clientMainPlayer.getPositionY() + "");
                        meteriorGamePlayerPositionX = clientMainPlayer.getPositionX();
                        meteriorGamePlayerPositionY = clientMainPlayer.getPositionY();
                    }
                }

                // collision detection

                for (int i = 0; i < players.size(); i++) {
                    players.get(i).render(gc);

                }

                for (int i = 0; i < asteroids.size(); i++) {
                    asteroids.get(i).render(gc);

                    for (int j = 0; j < players.size(); j++) {
                        if (players.get(j).intersects(asteroids.get(i))) {

                            if (isMeteorGameStartPrepareFinish == true) {
                                client.sendPacket(5, Settings._REQUEST_METEORGAME_METEOR_DELETE
                                        + "", getsRoomName(), client.getClientName(), asteroids
                                        .get(i).getPositionX() + "", asteroids.get(i)
                                        .getPositionY() + "");
                                asteroids.remove(i);

                            }
                        }

                    }
                }

            }
        };

        spriteAnimationTimer.start();

    }

    /**
     * init drawing about TicTacToc
     */
    private void drawShapesTictactoc() {
        Sprite board = new Sprite("total", 0, 0, 64, 64);
        Platform.runLater(() -> {
            for (int i = 0; i < Settings.nTicTacTocBlockWidth; i++)
                for (int j = 0; j < Settings.nTicTacTocBlockHeight; j++)
                    drawImage(board, Settings.nTicTacTocImageWidth * i,
                            Settings.nTicTacTocImageHeight * j, Settings.nTicTacTocImageWidth,
                            Settings.nTicTacTocImageHeight);
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
                    drawImage(board, Settings.nCatchMeImageWidth * i, Settings.nCatchMeImageHeight
                            * j, Settings.nCatchMeImageWidth, Settings.nCatchMeImageHeight);
        });

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
            case RIGHT:
            case UP:
                input.clear();
            case DOWN:
                String code = e.getCode().toString();
                if (!input.contains(code))
                    input.add(code);
                break;

            default:
                e.consume();
                break;
        }
    }

    /**
     * handle the sending message in the game room
     */
    private void sendingMessageInTheRoom() {
        String packet = cheatingTextEdit.getText();
        if (packet.length() > 0) {
            cheatingTextEdit.clear();

            client.sendPacket(3, Settings._REQUEST_ROOM_MEMEBER_MESSAGE + "", packet,
                    getsRoomName());
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
            client.sendPacket(4, Settings._REQUEST_START_THE_GAME + "", client.getClientName(),
                    getsRoomName(), Settings.isGamePrepareStart + "");

        } else if (btnStartCancel.getText().equals("취소")) {
            client.sendPacket(4, Settings._REQUEST_START_THE_GAME + "", client.getClientName(),
                    getsRoomName(), Settings.isGamePrepareStop + "");
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
        client.sendPacket(2, Settings._REQUEST_OUT_OF_THE_ROOM + "", client.getClientName());

    }

    /**
     * if client out the room successfully, scene change to waitingroom
     */
    public void outOfTheRoom() {
        if (getnGameType() == Settings.nGameMeteorGame) {
            spriteAnimationTimer.stop();
            client.sendPacket(3, Settings._REQUEST_METEORGAME_OUT_OF_PLAYER + "", getsRoomName(),
                    client.getClientName());

        }
        Stage stage = (Stage)btnCancel.getScene().getWindow();

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/room.fxml"));
                Parent loginScene = (Parent)loader.load();
                Scene scene = new Scene(loginScene);
                scene.getStylesheets().add(getClass().getResource("/Css/app.css").toString());
                WaitingRoomsManagerController waitingRoomsManagerController = loader
                        .getController();
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

        client.sendPacket(3, Settings._REQUEST_ANWER_ROOM_GAME_MESSAGE + "", getsRoomName(),
                packet[1]);
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
                        ticTacTocMultipeColorboard[i - 1] = new Sprite("total", 64 * (i % 3),
                                64 * (i / 3), 64, 64);

                    File file = new File("src/Asset/total.png");
                    Image image = new Image(file.toURI().toString());

                    if (isTicTacTocTwoColorMode == false) {
                        imageGameMainView.setImage(image);
                        int _tag = client.getClientUniqueGameTagNumber();
                        _tag++;

                        if (_tag >= this.nEnteredMember)
                            _tag = 0;

                        imageGameMainView.setViewport(new Rectangle2D(
                                ticTacTocMultipeColorboard[_tag].getImageStartX(),
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
                client.sendPacket(3, Settings._REQUES_TTICTACTOC_TURN_PLAYER_NAME + "",
                        getsRoomName(), client.getClientName());
            } else
                setPlayToken(false);

            if (getnGameType() == Settings.nGameCatchMe && isPlayToken()) {
                client.sendPacket(1, Settings._REQUEST_CATCHME_SELECT_ITEM + "");
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
                players.get(i).setPosition(Double.parseDouble(packet[2]),
                        Double.parseDouble(packet[3]));
        }

    }

    public void initMeteorGamePlayerGamePosition(String[] packet) {
        if (Settings.ERRORCODE != checkPlayerInTheGame(packet[1]))
            return;

        Player player = new Player("box.png", 100, 100);
        player.setPosition(Double.parseDouble(packet[2]), Double.parseDouble(packet[3]));
        player.setImageSize(30, 30);
        player.setsPlayerName(packet[1]);
        players.add(player);

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getsPlayerName().equals(client.getClientName())) {
                client.sendPacket(6, Settings._REQUEST_METEORGAME_REINIT_GAME_PLAY + "",
                        getsRoomName(), packet[1], client.getClientName(), players.get(i)
                                .getPositionX() + "", players.get(i).getPositionY() + "");
                clientMainPlayer = players.get(i);
            }

        }

    }

    private int checkPlayerInTheGame(String name) {
        for (int i = 0; i < players.size(); i++)
            if (players.get(i).getsPlayerName().equals(name))
                return i;
        return Settings.ERRORCODE;
    }

    public void initREMeteorGamePlayerGamePosition(String[] packet) {
        boolean isExisted = false;

        if (client.getClientName().equals(packet[1]))
            if (!client.getClientName().equals(packet[2])) {
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).getsPlayerName().equals(packet[2])) {
                        isExisted = true;
                        break;
                    }
                }

                if (isExisted == false) {
                    Player player = new Player("box.png", 100, 100);
                    player.setPosition(Double.parseDouble(packet[3]), Double.parseDouble(packet[4]));
                    player.setImageSize(30, 30);
                    player.setsPlayerName(packet[2]);
                    players.add(player);
                }
            }

    }

    public void outOfPlayerInMeteorGame(String[] packet) {
        players.remove(checkPlayerInTheGame(packet[1]));
    }

    public void initMeteorGameWhenStartGame(String[] packet) {
        Player asteroid = new Player("box.png", 0, 100, 100, 100);
        asteroid.setPosition(Double.parseDouble(packet[1]), Double.parseDouble(packet[2]));
        asteroid.setImageSize(5, 5);
        asteroid.setsPlayerName("asteroid");
        asteroids.add(asteroid);
    }

    public void StartPrepareCompleteMeteorGame(String[] packet) {
        isMeteorGameStartPrepareFinish = Boolean.parseBoolean(packet[1]);
        Platform.runLater(() -> displayText("Game Start!!"));
    }

    // 2 player name 3 position x 4 position y
    public void updateMeteorGamePlayerPosition(String[] packet) {
        if (client.getClientName().equals(packet[1]))
            return;

        for (int i = 0; i < players.size(); i++)
            if (players.get(i).getsPlayerName().equals(packet[1])) {
                players.get(i).setPosition(Double.parseDouble(packet[2]),
                        Double.parseDouble(packet[3]));
            }
    }

    // 2 player name 3 position x 4 position y
    public void deleteMeteorGameMeteor(String[] packet) {
        if (client.getClientName().equals(packet[1])) {
            nMeteorGameDestroyCount++;
            Platform.runLater(() -> {
                lbPlayerTurnBlockText.setText("count : " + nMeteorGameDestroyCount);
            });
            Platform.runLater(() -> {
                lbPlayerTurn.setText(packet[4]);
            });
            return;
        }
        if (isMeteorGameStartPrepareFinish == false)
            return;

        for (int i = 0; i < asteroids.size(); i++)
            if (asteroids.get(i).getPositionX() == Double.parseDouble(packet[2])
                    && asteroids.get(i).getPositionY() == Double.parseDouble(packet[3])) {
                asteroids.remove(i);
            }
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
                if (anchorPane.getChildren().get(i).getLayoutX() == _x
                        * Settings.nCatchMeImageWidth
                        && anchorPane.getChildren().get(i).getLayoutY() == _y
                                * Settings.nCatchMeImageHeight) {
                    File file = new File("src/Asset/start_moon.png");
                    Image image = new Image(file.toURI().toString());
                    imageGameMainView.setImage(image);
                    ((ImageView)anchorPane.getChildren().get(i)).setImage(image);
                    ((ImageView)anchorPane.getChildren().get(i)).setViewport(new Rectangle2D(67, 0,
                            67, 67));

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
                        if (anchorPane.getChildren().get(i).getLayoutX() == x
                                * Settings.nCatchMeImageWidth
                                && anchorPane.getChildren().get(i).getLayoutY() == y
                                        * Settings.nCatchMeImageHeight)
                            anchorPane.getChildren().remove(i);
                    }
                    drawImage(board, Settings.nCatchMeImageWidth * x, Settings.nCatchMeImageHeight
                            * y, Settings.nCatchMeImageWidth, Settings.nCatchMeImageHeight);

                });

                client.sendPacket(3, Settings._REQUES_TTICTACTOC_TURN_PLAYER_NAME + "",
                        getsRoomName(), client.getClientName());

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
                        imageGameMainView.setViewport(new Rectangle2D(108 * _setTheItemview, 93,
                                108, 93));
                    });

                } else {

                    setnCatchmeItem(Settings.ERRORCODE);

                    setPlayToken(false);
                }

            } else {
                Sprite board = new Sprite("start_moon", 0, 0, 67, 67);
                Platform.runLater(() -> {
                    for (int i = 0; i < anchorPane.getChildren().size(); i++) {
                        if (anchorPane.getChildren().get(i).getLayoutX() == x
                                * Settings.nCatchMeImageWidth
                                && anchorPane.getChildren().get(i).getLayoutY() == y
                                        * Settings.nCatchMeImageHeight)
                            anchorPane.getChildren().remove(i);
                    }
                    drawImage(board, Settings.nCatchMeImageWidth * x, Settings.nCatchMeImageHeight
                            * y, Settings.nCatchMeImageWidth, Settings.nCatchMeImageHeight);

                });

                if (Integer.parseInt(packet[5]) == client.getClientUniqueGameTagNumber()) {
                    setPlayToken(true);

                    Platform.runLater(() -> {
                        setnCatchmeItem(_setTheItemview);
                        File file = new File("src/Asset/buttons.png");
                        Image image = new Image(file.toURI().toString());
                        imageGameMainView.setImage(image);
                        imageGameMainView.setViewport(new Rectangle2D(108 * _setTheItemview, 93,
                                108, 93));
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
                        if (anchorPane.getChildren().get(i).getLayoutX() == x
                                * Settings.nTicTacTocImageWidth
                                && anchorPane.getChildren().get(i).getLayoutY() == y
                                        * Settings.nTicTacTocImageHeight)
                            anchorPane.getChildren().remove(i);
                    }
                    if (isTicTacTocTwoColorMode)
                        drawImage(board, Settings.nTicTacTocImageWidth * x,
                                Settings.nTicTacTocImageHeight * y, Settings.nTicTacTocImageWidth,
                                Settings.nTicTacTocImageHeight);
                    else
                        drawImage(ticTacTocMultipeColorboard[Integer.parseInt(packet[5])],
                                Settings.nTicTacTocImageWidth * x, Settings.nTicTacTocImageHeight
                                        * y, Settings.nTicTacTocImageWidth,
                                Settings.nTicTacTocImageHeight);

                });

                client.sendPacket(3, Settings._REQUES_TTICTACTOC_TURN_PLAYER_NAME + "",
                        getsRoomName(), client.getClientName());

                if (Integer.parseInt(packet[5]) == client.getClientUniqueGameTagNumber())
                    setPlayToken(true);

            } else {
                Sprite board = new Sprite("total", 64, 0, 64, 64);
                Sprite boardSecond = new Sprite("total", 0, 64, 64, 64);
                Platform.runLater(() -> {
                    for (int i = 0; i < anchorPane.getChildren().size(); i++) {
                        if (anchorPane.getChildren().get(i).getLayoutX() == x
                                * Settings.nTicTacTocImageWidth
                                && anchorPane.getChildren().get(i).getLayoutY() == y
                                        * Settings.nTicTacTocImageHeight)
                            anchorPane.getChildren().remove(i);
                    }
                    if (isTicTacTocTwoColorMode) {
                        drawImage(board, Settings.nTicTacTocImageWidth * x,
                                Settings.nTicTacTocImageHeight * y, Settings.nTicTacTocImageWidth,
                                Settings.nTicTacTocImageHeight);
                    } else {
                        if (nEnteredMember > 1)
                            drawImage(ticTacTocMultipeColorboard[Integer.parseInt(packet[5])],
                                    Settings.nTicTacTocImageWidth * x,
                                    Settings.nTicTacTocImageHeight * y,
                                    Settings.nTicTacTocImageWidth, Settings.nTicTacTocImageHeight);
                        else
                            drawImage(boardSecond, Settings.nTicTacTocImageWidth * x,
                                    Settings.nTicTacTocImageHeight * y,
                                    Settings.nTicTacTocImageWidth, Settings.nTicTacTocImageHeight);
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
                ImageView imageView = (ImageView)parent.lookup("#imgMessage");
                imageView.setImage(new Image(getClass().getResource("/Css/dialog-info.png")
                        .toString()));
                imageView.setOnMouseClicked(event -> popup.hide());
                Label lblMessage = (Label)parent.lookup("#lblMessage");
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
        client.sendPacket(2, Settings._REQUEST_NEW_ROOM_MEMEBER_NOTIFICATION + "", getsRoomName());
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
        client.sendPacket(1, Settings._REQUEST_SET_GAME_PLAYER_UNIQUE_TAG_NUMBER + "");
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
            ;
            imageGameMainView.setVisible(false);
            drawSpriteImageView();
        }

        client.sendPacket(2, Settings._REQUEST_GAME_ROOM_MEMEBER_NUMBER + "", sRoomName);
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

                TextField txt_TextField = (TextField)e.getSource();
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

}
