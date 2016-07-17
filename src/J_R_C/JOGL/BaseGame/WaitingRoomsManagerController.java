
package J_R_C.JOGL.BaseGame;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author KJW finish at 2016/ 02/ 15
 * @version 1.0.0v
 * @description this class for the Waiting Room, this class manage the All of
 *              event in the WaitingRoom.
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class WaitingRoomsManagerController implements Initializable {

    private final int ROOMNAMEMAXIMUMLENGTH = 5;

    private final int ROOMMAMBERMAXIMUMLENGTH = 1;

    private final int SENDINGMESSAGEMAXLENGTH = 70;

    // Label
    /**
     * Main Panel
     */
    @FXML
    private AnchorPane mainPane;

    /**
     * label for the game room Name
     */
    @FXML
    private Label lbRoomName;

    /**
     * label for the maximum number about game room
     */
    @FXML
    private Label lbMaximumMember;

    /**
     * label for the entered Client number about game room
     */
    @FXML
    private Label lbEnterMemeber;

    /**
     * label for the game style
     */
    @FXML
    private Label lbGameStyle;

    /**
     * button for the enter the Room
     */
    @FXML
    private Button btnEnter;

    /**
     * button for the return to main scene
     */
    @FXML
    private Button btnCancel;

    /**
     * button for sending message to each clients who is in the waiting room
     */
    @FXML
    private Button btnSendMessage;

    /**
     * show the image about game style
     */
    @FXML
    private ImageView imageGameMainView;

    /**
     * show the existed room in the waiting room game list
     */
    @FXML
    private ListView<String> roomListView;

    /**
     * for cheating show the text
     */
    @FXML
    private TextField cheatingTextEdit;

    /**
     * for cheating insert text
     */
    @FXML
    private TextArea cheatingTextArea;

    /**
     * for the list
     */
    private ObservableList<String> items;

    /**
     * client's number about entered the game room
     */
    private int enteredRoomMember;

    /**
     * show the maximum member number
     */
    private int maxRoomMember;

    /**
     * for the game type
     */
    private int gameType;

    /**
     * check the event about client click the room list
     */
    private boolean isClickTheRoomlist;

    /**
     * save the previous stage for change the scene
     */
    private Stage primaryStage;

    /**
     * client variable
     */
    private ServerClient client;

    /**
     * event scheduler for the runnable
     */
    private ScheduledExecutorService scheduler;

    /*
     * (non-Javadoc) init the all state about waiting room
     * @see javafx.fxml.Initializable#initialize(java.net.URL,
     * java.util.ResourceBundle)
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isClickTheRoomlist = false;

        // ImageView Initialization
        File file = new File("src/Asset/img_pangpang_32.png");
        Image image = new Image(file.toURI().toString());
        imageGameMainView.setImage(image);

        items = FXCollections.observableArrayList();
        roomListView.setItems(items);
        roomListView.setFocusTraversable(false);

        // set the room list sytle
        roomListView
                .setStyle("-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color;-fx-background-insets: 0, 1, 2;-fx-background-radius: 5, 4, 3;");

        // register the click event
        roomListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                // if click event is occurred. then change the label for showing
                // room information
                if (null != roomListView.getSelectionModel().getSelectedItem()) {
                    isClickTheRoomlist = true;
                    client.sendPacket(2, Settings._REQUEST_CLICKED_ROOM_INFORMATION + "",
                            roomListView.getSelectionModel().getSelectedItem());

                    lbRoomName.setText(roomListView.getSelectionModel().getSelectedItem());
                }

                // if double click event is occurred, then enter the game room
                if (event.getClickCount() == 2) {
                    handleBtnEnter(null);
                }
            }
        });

        // check all time for special variable
        scheduler = Executors.newScheduledThreadPool(2);
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

                        if (true == isClickTheRoomlist) {

                            if (enteredRoomMember != -1 && !items.isEmpty())
                                lbEnterMemeber.setText("" + enteredRoomMember);
                        }

                        // draw game image
                        if (enteredRoomMember != -1 && !items.isEmpty())
                            if (Settings.nGameCatchMe == gameType) {

                                lbGameStyle.setText(Settings.sGameStringStyleCatchMe);
                                File file = new File("src/Asset/dirt64.png");
                                Image image = new Image(file.toURI().toString());
                                imageGameMainView.setImage(image);
                            } else if (Settings.nGameTicTacToc == gameType) {

                                lbGameStyle.setText(Settings.sGameStringStyleTicTacToc);
                                File file = new File("src/Asset/grass64.png");
                                Image image = new Image(file.toURI().toString());
                                imageGameMainView.setImage(image);
                            }

                        // request about room member number to server
                        if (!lbRoomName.getText().equals(" "))
                            client.sendPacket(2, Settings._REQUEST_ROOM_MEMBER_NUMBER + "",
                                    lbRoomName.getText());

                        client.sendPacket(1, Settings._REQUEST_GAME_ROOM_LIST + "");

                        // if disconnected server then this code is operated
                        if (client.getIsServerConnected() == ServerClient.SERVERCONNECTIONFAIL) {

                            handleBtnCancel(null);
                            handlePopup("서버와의 연결이 끊겼습니다.");

                        }

                        // if server disconnected then this window is terminated
                        if (client.getIsServerConnected() == ServerClient.SERVERCONNECTIONFAIL)
                            dialog.close();

                    }
                });

            }
            // set the interval
        }, 100, 100, TimeUnit.MILLISECONDS);

        // set the cheating text area
        cheatingTextArea.setEditable(false);
        cheatingTextArea
                .setStyle("-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color;-fx-background-insets: 0, 1, 2;-fx-background-radius: 5, 4, 3;");
        cheatingTextEdit.addEventFilter(KeyEvent.KEY_TYPED,
                message_text_Validation(SENDINGMESSAGEMAXLENGTH));

        btnEnter.setOnAction(e -> handleBtnEnter(e));
        btnCancel.setOnAction(e -> handleBtnCancel(e));
        btnSendMessage.setOnAction(e -> handleBtnSendingMessage(e));
        cheatingTextEdit.setOnKeyPressed(e -> handleBtnKeyEvent(e));
        client = LoginController.getClient();
        client.setController(this);

        // display first line
        displayText("Welcome to The J.R.C Game World [" + client.getClientName() + "]");

    }

    /**
     * handle the client key event about cheatingtextarea
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
            default:
                e.consume();
                break;
        }
    }

    /**
     * handle all popUp windows
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
     * set the primary stage
     * 
     * @param primaryStage
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * if client click the sending button then this method is called
     * 
     * @param event
     */
    public void handleBtnSendingMessage(ActionEvent event) {

        sendingMessageInTheRoom();
    }

    /**
     * sending message in the room people
     */
    private void sendingMessageInTheRoom() {
        String packet = cheatingTextEdit.getText();
        if (packet.length() > 0) {
            cheatingTextEdit.clear();

            client.sendPacket(2, Settings._REQUEST_WAITING_ROOMS_ENDING_MESSAGE + "", packet);
        }
    }

    /**
     * if client click the cancel button, then this method is called
     * 
     * @param event
     */
    public void handleBtnCancel(ActionEvent event) {

        Stage stage = (Stage)mainPane.getScene().getWindow();

        client.sendPacket(2, Settings._REQUEST_OUT_OF_THE_SERVER + "", client.getClientName());

        client.sendPacket(1, Settings._REQUEST_LOGOUT + "");

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
     * if client make existed room, server tell client that this room is existed
     * in the game room list
     */
    public void popupTheMessageTheRoomIsExist() {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                handlePopup("[" + txtFieldName.getText().toString() + "] 방은 이미 존재합니다.");
            }
        });

    }

    /**
     * set the label about entered client number
     * 
     * @param clientNumber
     */
    public void setTheRoomEnteredClient(String clientNumber) {
        enteredRoomMember = Integer.parseInt(clientNumber);
    }

    /**
     * set the label about maximum member, game type
     * 
     * @param receive
     */
    public void setTheRoomImformation(String[] receive) {
        maxRoomMember = Integer.parseInt(receive[1]);
        gameType = Integer.parseInt(receive[2]);

        Platform.runLater(() -> lbMaximumMember.setText("" + maxRoomMember));
    }

    /**
     * display the room message on the display panel
     * 
     * @param packet
     */
    public void setTheRoomMessage(String[] packet) {
        Platform.runLater(() -> displayText("[" + packet[2] + "] " + packet[1]));
    }

    /**
     * for displaying text
     * 
     * @param text
     */
    private void displayText(String text) {
        cheatingTextArea.appendText(text + "\n");
    }

    /**
     * if client make new room, then this method is called
     * 
     * @param packet
     */
    public void enterTheNewGameRoom(String[] packet) {

        scheduler.shutdown();

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/game_room.fxml"));

                Parent roomList;
                roomList = (Parent)loader.load();
                Scene scene = new Scene(roomList);
                scene.getStylesheets().add(getClass().getResource("/Css/app.css").toString());
                GameRoomController gameRoomController = loader.getController();
                gameRoomController.setPrimaryStage(primaryStage);
                gameRoomController.setsRoomName(packet[1]);
                gameRoomController.setnToTalClient(Integer.parseInt(packet[2]));
                gameRoomController.setnGameType(Integer.parseInt(packet[3]));
                gameRoomController.initTurnPlayerName(packet[4]);
                primaryStage.setScene(scene);
                primaryStage.show();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    /**
     * add the room information to the room list
     * 
     * @param receive
     */
    public void addTheRoom(String[] receive) {

        int length = Integer.parseInt(receive[1]);
        int total = items.size();
        boolean tag[] = new boolean[total];

        Platform.runLater(() -> {

            for (int j = 2; j < length + 2; j++)
                if (items.contains(receive[j]) == false)
                    items.add(receive[j]);
                else if (tag.length > 0 && items.size() > 0 && j > 0 && receive.length > 0)
                    tag[items.indexOf(receive[j])] = true;

            for (int i = total - 1; i > -1; i--)
                if (i > -1 && items.size() > 0 && tag[i] == false)
                    items.remove(i);

            if (0 == items.size()) {
                File file = new File("src/Asset/img_pangpang_32.png");
                Image image = new Image(file.toURI().toString());
                imageGameMainView.setImage(image);
                lbRoomName.setText(" ");

                lbMaximumMember.setText(" ");

                lbEnterMemeber.setText(" ");

                lbGameStyle.setText(" ");
            }

        });

    }

    /**
     * Dialog Part about creating room
     */

    /**
     * room name
     */
    private javafx.scene.control.TextField txtFieldName;

    /**
     * maximum room client number
     */
    private javafx.scene.control.TextField txtFieldNumber;

    /**
     * game sort
     */
    private int nSelectedGame;

    /**
     * dialog variable
     */
    private Stage dialog;

    public void handleCustom(ActionEvent e) throws Exception {
        dialog = new Stage(StageStyle.UTILITY);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(((LoginController)client.getLoginController()).getPrimaryStage());
        dialog.setTitle("방 만들기");

        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/custom_dialog.fxml"));

        Button btnOk = (Button)parent.lookup("#btnOk");
        btnOk.setOnAction(event -> handleBtnCreateRoom(event));

        Button btnCancel = (Button)parent.lookup("#btnCancel");
        btnCancel.setOnAction(event -> dialog.close());

        File file = new File("src/Asset/dirt64.png");
        Image image = new Image(file.toURI().toString());

        ImageView imgViewGame = (ImageView)parent.lookup("#imgViewGame");
        imgViewGame.setImage(image);

        txtFieldName = (javafx.scene.control.TextField)parent.lookup("#textEditRoomName");
        txtFieldName.setPromptText("Input Room Name 5 String");
        txtFieldName.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(ROOMNAMEMAXIMUMLENGTH));

        txtFieldNumber = (javafx.scene.control.TextField)parent
                .lookup("#textEditClientEnterNumber");
        txtFieldNumber.setPromptText("MAXIMUM 8");
        txtFieldNumber.addEventFilter(KeyEvent.KEY_TYPED,
                numeric_Validation(ROOMMAMBERMAXIMUMLENGTH));

        nSelectedGame = Settings.nGameCatchMe;

        @SuppressWarnings("unchecked")
        ComboBox<String> gameSytleComboBox = (ComboBox<String>)parent.lookup("#gameStyleComboBox");
        gameSytleComboBox.setValue(Settings.sGameStringStyleCatchMe);
        gameSytleComboBox.getItems().addAll(Settings.sGameStringStyleCatchMe,
                Settings.sGameStringStyleTicTacToc, Settings.sGameStringStyleMeteorGame,
                Settings.sGameStringStyleSiegeWarefare);
        /*
         * check about the clicke event , for selecting game image
         */

        gameSytleComboBox.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {
                        if (newValue.equals(Settings.sGameStringStyleCatchMe)) {
                            File file = new File("src/Asset/dirt64.png");
                            Image image = new Image(file.toURI().toString());
                            nSelectedGame = Settings.nGameCatchMe;
                            imgViewGame.setImage(image);

                        } else if (newValue.equals(Settings.sGameStringStyleTicTacToc)) {
                            File file = new File("src/Asset/grass64.png");
                            Image image = new Image(file.toURI().toString());
                            nSelectedGame = Settings.nGameTicTacToc;
                            imgViewGame.setImage(image);
                        } else if (newValue.equals(Settings.sGameStringStyleMeteorGame)) {
                            File file = new File("src/Asset/sky64.png");
                            Image image = new Image(file.toURI().toString());
                            nSelectedGame = Settings.nGameMeteorGame;
                            imgViewGame.setImage(image);
                        } else if (newValue.equals(Settings.sGameStringStyleSiegeWarefare)) {
                            File file = new File("src/Asset/sky64.png");
                            Image image = new Image(file.toURI().toString());
                            nSelectedGame = Settings.nGameSiegeWarefare;
                            imgViewGame.setImage(image);
                        }
                    }

                });

        Scene scene = new Scene(parent);

        dialog.setScene(scene);
        dialog.setResizable(false);
        dialog.show();

    }

    /**
     * if client click the entered button, then this method is called
     * 
     * @param event
     */
    public void handleBtnEnter(ActionEvent event) {

        client.sendPacket(3, Settings._REQUEST_GUEST_ENTER_THE_ROOM + "", roomListView
                .getSelectionModel().getSelectedItem(), client.getClientName());

    }

    /**
     * if client click the make room button, then this method is called
     * 
     * @param event
     */
    public void handleBtnCreateRoom(ActionEvent event) {

        if (!txtFieldName.getText().isEmpty() && !txtFieldNumber.getText().isEmpty()) {

            client.sendPacket(4, Settings._REQUEST_THE_ROOM_LIST_INFORMATION + "", txtFieldName
                    .getText().toString(), Integer.parseInt(txtFieldNumber.getText().toString())
                    + "", nSelectedGame + "");

            dialog.close();
        }
    }

    /**
     * check the room client number syntax
     * 
     * @param max_Lengh
     * @return
     */
    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {

                TextField txt_TextField = (TextField)e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if (e.getCharacter().matches("[0-8.]")) {
                    if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
                        e.consume();
                    } else if (txt_TextField.getText().length() == 0
                            && e.getCharacter().matches("[.]")) {
                        e.consume();
                    }
                } else {
                    e.consume();
                }

            }
        };
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
     * check the room name syntax
     * 
     * @param max_Lengh
     * @return
     */
    public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField)e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }

                if (e.getCharacter().matches("[A-Za-z]") && txt_TextField.getText().length() < 1) {
                } else if (txt_TextField.getText().length() < 1) {
                    e.consume();
                }

            }
        };
    }

}
