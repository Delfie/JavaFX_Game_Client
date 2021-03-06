
package J_R_C.JOGL.BaseGame;

/**
 * @author KJW finish at 2016/ 08/ 11
 * @version 2.0.0v
 * @description this class Manage The Global Constant about NetWork Protocol or
 *              Program Constant
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class Settings {

	// Game Network Protocol
	/**
	 * Error Code -1
	 */
	public static final int ERRORCODE = -1;

	public static final int ZEROINIT = 0;

	/**
	 * Check the client Access Status 1
	 */
	public static final int _ACCESS_THE_SERVER = 0x00000001;

	/**
	 * Request the Room List Information 2
	 */
	public static final int _REQUEST_THE_ROOM_LIST_INFORMATION = 0x00000002;

	/**
	 * Request the Make Room 3
	 */
	public static final int _REQUEST_THE_MAKE_ROOM = 0x00000003;

	/**
	 * Request The List View Clicked Item Information 4
	 */
	public static final int _REQUEST_CLICKED_ROOM_INFORMATION = 0x00000004;

	/**
	 * Answer The Room is Exist 5
	 */
	public static final int _ANSWER_THE_ROOM_IS_EXIST = 0x00000005;

	/**
	 * Request The Room Member Number 6
	 */
	public static final int _REQUEST_ROOM_MEMBER_NUMBER = 0x00000006;

	/**
	 * Answer The Room member Number 7
	 */
	public static final int _ANSWER_ROOM_MEMBER_NUMBER = 0x00000007;

	/**
	 * Request The Game Room List 8
	 */
	public static final int _REQUEST_GAME_ROOM_LIST = 0x00000008;

	/**
	 * Answer The Game Room List 9
	 */
	public static final int _ANSWER_GAME_ROOM_LIST = 0x00000009;

	/**
	 * Answer The List View Clicked Item Information 10
	 */
	public static final int _ANSWER_CLICKED_ROOM_INFORMATION = 0x0000000a;

	/**
	 * Request The Wait Room Clients Sending Message 11
	 */
	public static final int _REQUEST_WAITING_ROOM_SENDING_MESSAGE = 0x0000000b;
	/**
	 * Answer The Wait Room Clients Sending Message 12
	 */
	public static final int _ANSWER_WAITING_ROOM_SENDING_MESSAGE = 0x0000000c;

	/**
	 * Request the Login 13
	 */
	public static final int _REQUEST_LOGIN = 0x0000000d;

	/**
	 * Answer The Login 14
	 */
	public static final int _ANSWER_LOGIN = 0x0000000e;

	/**
	 * Answer The Room Making Successful 15
	 */
	public static final int _ANSWER_THE_ROOM_MAKE_SUCCESS = 0x0000000f;

	/**
	 * Request The Out of the Room 16
	 */
	public static final int _REQUEST_OUT_OF_THE_ROOM = 0x00000010;

	/**
	 * Request The Guest Enter The Room 17
	 */
	public static final int _REQUEST_GUEST_ENTER_THE_ROOM = 0x00000011;

	/**
	 * Answer The Guest Enter The Room Success 18
	 */
	public static final int _ANSWER_GUEST_ENTER_THE_ROOM_SUCCESS = 0x00000012;

	/**
	 * Answer The Guest Enter The Room Fail 19
	 */
	public static final int _ANSWER_GUEST_ENTER_THE_ROOM_FAIL = 0x00000013;

	/**
	 * Answer The Guest Enter The Room Error 20
	 */
	public static final int _ANSWER_GUEST_ENTER_THE_ROOM_LOGICAL_ERROR = 0x00000014;

	/**
	 * Request Game Room Member Number 21
	 */
	public static final int _REQUEST_GAME_ROOM_MEMEBER_NUMBER = 0x00000015;

	/**
	 * Answer Game Room Member Number 22
	 */
	public static final int _ANSWER_GAME_ROOM_MEMEBER_NUMBER = 0x00000016;

	/**
	 * Answer Out of The Room 23
	 */
	public static final int _ANSWER_OUT_OF_THE_ROOM = 0x00000017;

	/**
	 * Request Terminate the client 24
	 */
	public static final int _REQUEST_TERMINATE = 0x00000018;

	/**
	 * Request Guest is Sending Message to each other in the Game Room 25
	 */
	public static final int _REQUEST_ROOM_MEMEBER_MESSAGE = 0x00000019;

	/**
	 * Answer Guest is Sending Message to each other in The Game Room 26
	 */
	public static final int _ANSWER_ROOM_MEMEBER_MESSAGE = 0x0000001a;

	/**
	 * Answer New Room Guest Entered in the Room 27
	 */
	public static final int _ANSWER_NEW_ROOM_MEMEBER_NOTIFICATION = 0x0000001b;

	/**
	 * Request new Room Guest Entered in the Room 28
	 */
	public static final int _REQUEST_NEW_ROOM_MEMEBER_NOTIFICATION = 0x0000001c;

	/**
	 * Request The Game Start 29
	 */
	public static final int _REQUEST_START_THE_GAME = 0x0000001d;

	/**
	 * Answer The Game Start 30
	 */
	public static final int _ANSWER_START_THE_GAME = 0x0000001e;

	/**
	 * Answer Room Game Start 31
	 */
	public static final int _ANSWER_ROOM_GAME_START = 0x0000001f;

	/**
	 * Request Answer Room People Message 32
	 */
	public static final int _REQUEST_ANWER_ROOM_GAME_MESSAGE = 0x00000020;

	/**
	 * Request TicTacToc Game event 33
	 */
	public static final int _REQUEST_TICTACTOC_STONE_EVENT = 0x00000021;

	/**
	 * Answer TicTacToc Game event 34
	 */
	public static final int _ANSWER_TICTACTOC_STONE_EVENT = 0x00000022;

	/**
	 * Answer Set Game Player Unique tag Number 35
	 */
	public static final int _ANSWER_SET_GAME_PLAYER_UNIQUE_TAG_NUMBER = 0x00000023;

	/**
	 * Request TicTacToc player Name 36
	 */
	public static final int _REQUEST_TICTACTOC_TURN_PLAYER_NAME = 0x00000024;

	/**
	 * Answer TicTacToc player Name 37
	 */
	public static final int _ANSWER_TICTACTOC_TURN_PLAYER_NAME = 0x00000025;

	/**
	 * Request GamePlayer Unique Number 38
	 */
	public static final int _REQUEST_SET_GAME_PLAYER_UNIQUE_TAG_NUMBER = 0x00000026;

	/**
	 * Answer Game end 39
	 */
	public static final int _ANSWER_GAME_END = 0x00000027;

	/**
	 * Answer Game End Reverse 40
	 */
	public static final int _ANSWER_GAME_END_REVERSE = 0x00000028;

	/**
	 * Request Register New User 41
	 */
	public static final int _REQUEST_REGISTER_NEW_USER = 0x00000029;

	/**
	 * Answer Register New User 42
	 */
	public static final int _ANSWER_REGISTER_NEW_USER = 0x0000002a;

	/**
	 * Answer TicTacTocAi 43
	 */
	public static final int _ANSWER_TICTACTOC_AI = 0x0000002b;

	/**
	 * Request User Out Of the Server 44
	 */
	public static final int _REQUEST_OUT_OF_THE_SERVER = 0x0000002c;

	/**
	 * Request User Log Out 45
	 */
	public static final int _REQUEST_LOGOUT = 0x0000002d;

	/**
	 * Answer TicTacToc Game mode 46
	 */
	public static final int _ANSWER_SET_THE_TICTACTOC_COLOR_MODE = 0x0000002e;

	/**
	 * Answer TicTactoc Game Setting 47
	 */
	public static final int _ANSWER_TICTACTOC_AI_SETTING = 0x0000002f;

	/**
	 * Request CatchMe Game Item 48
	 */
	public static final int _REQUEST_CATCHME_SELECT_ITEM = 0x00000030;

	/**
	 * Answer CatchMe Game Item 49
	 */
	public static final int _ANSWER_CATCHME_SELECT_ITEM = 0x00000031;

	/**
	 * Request CatchMe Game Click Event 50
	 */
	public static final int _REQUEST_CATCHME_STONE_EVENT = 0x00000032;

	/**
	 * Answer CatchMe Game Click Event 51
	 */
	public static final int _ANSWER_CATCHME_STONE_EVENT = 0x00000033;

	/**
	 * Answer CatchMe User First Click init Event 52
	 */
	public static final int _ANSWER_CATCHME_INIT_PLAY_CLICKED_NUMBER = 0x00000034;

	/**
	 * Answer CatchMe Draw End of Path 53
	 */
	public static final int _ANSWER_CATCHME_DRAW_PATH = 0x00000035;

	/**
	 * Answer CatchMe User Click Count 54
	 */
	public static final int _ANSWER_SET_THE_CATCHME_PLAY_COUNT = 0x00000036;

	/**
	 * Request Mobile login to server 55
	 */
	public static final int _REQUEST_MOBILE_LOGIN = 0x00000037;

	/**
	 * Request Mobile login to server 56
	 */
	public static final int _REQUEST_MOBILE_MAKE_GAME_UNIVERSE = 0x00000038;

	/**
	 * Request Mobile login to server 57
	 */
	public static final int _ANSWER_MOBILE_MAKE_GAME_UNIVERSE = 0x00000039;

	/**
	 * Request METEORGAME STONE SET CLICK EVENT 58
	 */
	public static final int _REQUEST_METEORGAME_SET_CLIECK_EVENT = 0x0000003a;

	/**
	 * Answer METEORGAME STONE SET CLICK EVENT 59
	 */
	public static final int _ANSWER_METEORGAME_SET_CLIECK_EVENT = 0x0000003b;

	/**
	 * Request METEORGAME init Game Play 60
	 */
	public static final int _REQUEST_METEORGAME_INIT_GAME_PLAY = 0x0000003c;

	/**
	 * Answer METEORGAME init Game play 61
	 */
	public static final int _ANSWER_METEORGAME_INIT_GAME_PLAY = 0x0000003d;

	/**
	 * Request METEORGAME reinit Game play 62
	 */
	public static final int _REQUEST_METEORGAME_REINIT_GAME_PLAY = 0x0000003e;

	/**
	 * Answer METEORGAME reinit Game PLAY 63
	 */
	public static final int _ANSWER_METEORGAME_REINIT_GAME_PLAY = 0x0000003f;

	/**
	 * Request METEORGAME out event 64
	 */
	public static final int _REQUEST_METEORGAME_OUT_OF_PLAYER = 0x00000040;

	/**
	 * Answer MEETEORGAME out event 65
	 */
	public static final int _ANSWER_METEORGAME_OUT_OF_PLAYER = 0x00000041;

	/**
	 * Answer METEORGAME Universe Stone event 66
	 */
	public static final int _ANSWER_METEORGAME_UNIVERSE_INIT = 0x00000042;

	/**
	 * Request METEORGAME player movement event 67
	 */
	public static final int _REQUEST_METEORGAME_PLAYER_MOVING = 0x00000043;

	/**
	 * Answer METEORGAME player movement event 68
	 */
	public static final int _ANSWER_METEORGAME_PLAYER_MOVING = 0x00000044;

	/**
	 * Answer METEORGAME player game start 69
	 */
	public static final int _ANSWER_METEORGAME_PLAY_START = 0x00000045;

	/**
	 * Request METEORGAME meteor crash event 70
	 */
	public static final int _REQUEST_METEORGAME_METEOR_DELETE = 0x00000046;

	/**
	 * Answer METEORGAME meteor crash event 71
	 */
	public static final int _ANSWER_METEORGAME_METEOR_DELETE = 0x00000047;

	/**
	 * Request METEORGAME Game finish 72
	 */
	public static final int _REQUEST_METEORGAME_METEOR_GAME_FINISH = 0x00000048;

	/**
	 * Answer METEORGAME Game finish 73
	 */
	public static final int _ANSWER_METEORGAME_METEOR_GAME_FINISH = 0x00000049;

	/**
	 * Request Mobile PangPang Score 74
	 */
	public static final int _REQEUST_MOBILE_PANGPANG_SCORE = 0x0000004a;

	/**
	 * Answer Mobile PangPang Score from DB 75
	 */
	public static final int _ANSWER_MOBILE_PANGPANG_SCORE = 0x0000004b;

	/**
	 * Request PC-Client Version check event 76
	 */
	public static final int _REQUEST_PC_CLIENT_VERSION_CHECK = 0x0000004c;

	/**
	 * Answer PC-Client Version check event 77
	 */
	public static final int _ANSWER_PC_CLIENT_VERSION_CHECK = 0x0000004d;
	
	/**
	 * Request PANGPANG Game init event 78
	 */
	public static final int _REQUEST_PANGPANG_INIT_GAME_PLAY = 0x0000004e;

	/**
	 * Answer PANGAPNG Game init event 79
	 */
	public static final int _ANSWER_PANGPANG_INIT_GAME_PLAY = 0x0000004f;
	
	/**
	 * Request PANGAPNG Game reinit event 80
	 */
	public static final int _REQUEST_PANGPANG_REINIT_GAME_PLAY = 0x00000050;

	/**
	 * Answer PANGAPNG Game reinit event 81
	 */
	public static final int _ANSWER_PANGPANG_REINIT_GAME_PLAY = 0x00000051;
	
    /**
     * Answer PANGAPNG Game start 82
     */
    public static final int _ANSWER_PANGPANG_PLAY_START = 0x00000052;
    
	/**
	 * Request PANGAPNG Game player moving event 83
	 */
	public static final int _REQUEST_PANGPANG_PLAYER_MOVING = 0x00000053;

	/**
	 * Answer PANGAPNG Game player moving event 84
	 */
	public static final int _ANSWER_PANGPANG_PLAYER_MOVING = 0x00000054;

	/**
	 * Request METEORGAME player size UP 85
	 */
	public static final int _REQUEST_METEORGAME_METEOR_PLAYER_SIZE_UP = 0x00000055;
	
	/**
	 * Answer METEORGAME player size up 86
	 */
	public static final int _ANSWER_METEORGAME_METEOR_PLAYER_SIZE_UP = 0x00000056;
	
	/**
	 * Request PANGAPNG out of the room 87
	 */
	public static final int _REQUEST_PANGPANG_OUT_OF_PLAYER = 0x00000057;
	
	/**
	 * Answer PANGPANG out of the room 88
	 */
	public static final int _ANSWER_PANGPANG_OUT_OF_PLAYER = 0x00000058;
	
	/**
	 * Answer PANGAPNG Enemy information event 89
	 */
	public static final int _ANSWER_PANGPANG_ENEMY_EVENT = 0x00000059;
	/**
	 * Answer PANGAPNG Enemy init event 90
	 */
	public static final int _ANSWER_PANGPANG_ENEMY_INIT = 0x0000005a;
	
	/**
	 * Answer PANGAPNG Enemy Attack event 91
	 */
	public static final int _ANSWER_PANGAPNG_ENEMY_ATTACK = 0x0000005b;

	/**
	 * Request PANGAPNG player Attack event 92
	 */
	public static final int _REQUEST_PANGAPNG_ATTACK = 0x0000005c;
	
	/**
	 * Answer PangPang player Attack event 93
	 */
	public static final int _ANSWER_PANGAPNG_ATTACK = 0x0000005d;
	
	/**
	 * Request PangPang Enemy Collision event 94
	 */
	public static final int _REQUEST_PANGAPNG_ENEMY_COLLISION_EVENT = 0x0000005e;
	
	/**
	 * Answer PangPang Enemy Collision event 95
	 */
	public static final int _ANSWER_PANGAPNG_ENEMY_COLLISION_EVENT = 0x0000005f;
	
	/**
	 * Request PangPang Game finish defeat 96
	 */
	public static final int _REQUEST_PANGAPNG_FINISH = 0x00000060;
	
	/**
	 * Request PangPang game finish win 97
	 */
	public static final int _REQUEST_PANGAPNG_FINISH_WIN = 0x00000061;
	
	/**
	 * Request PangPang Player Death 98
	 */
	public static final int _REQUEST_PANGAPNG_PLAYER_DEATH = 0x00000062;

	/**
	 * Answer PangPang Player Death 99
	 */
	public static final int _ANSWER_PANGAPNG_PLAYER_DEATH = 0x00000062;
	
	/**
	 * Error Packet
	 */
	public static final int _ERROR_PACKET = 0xffffffff;
	
	// Asset Constant

	/**
	 * this Valuable using for setting the GameScene Size Width
	 */
	public static final int nGameScreenWidth = 480;

	/**
	 * this Valuable using for setting the GameScene Size Height
	 */
	public static final int nGameScreenHeight = 480;

	/**
	 * this Valuable using for setting the Application Name
	 */
	public static final String sGameTitle = "PANGPANG RPG";

	/**
	 * this Valuable using for setting the path of the loading resource
	 * location.
	 */
	public static final String sGameResourceDefaultLoadPath = "asset/";

	/**
	 * this Valuable using for setting the Default Resource You Must Input
	 * Capital
	 */
	public static final String sGameResourceDefaultExtension = "PNG";

	/**
	 * Resource Loading Path
	 */
	public static final String APRITESHEET_IMAGE_LOCATION = "C:/Users/KJW/workspace/J.R.C_JAVA_GAME_Engine_FX/src/asset/";

	/**
	 * game String Constant CatchMe
	 */
	public static final String sGameStringStyleCatchMe = "�������";

	/**
	 * game String Constant TicTacToc
	 */
	public static final String sGameStringStyleTicTacToc = "����";

	/**
	 * game String Constant TicTacToc
	 */
	public static final String sGameStringStyleMeteorGame = "�����";

	public static final String sGameStringStylePangPang = "����";

	/**
	 * Game CatchMe Constant Count
	 */
	public static final int nGameCatchMe = 1;

	/**
	 * Game TicTacToc Constant Count
	 */
	public static final int nGameTicTacToc = 2;

	/**
	 * Game MeteorGame Constant Count
	 */
	public static final int nGameMeteorGame = 3;

	public static final int nGamePangPang = 4;

	/**
	 * Game CatchMe Item Left Constant 0
	 */
	public static final int nCatchMeItemLeft = 0;

	/**
	 * Game CatchMe Item Down Constant 1
	 */
	public static final int nCatchMeItemDown = 1;

	/**
	 * Game CatchMe Item Up Constant 2
	 */
	public static final int nCatchMeItemUp = 2;

	/**
	 * Game CatchMe Item Right Constant 3
	 */
	public static final int nCatchMeItemRight = 3;

	/**
	 * game prepare prepare constant
	 */
	public static final boolean isGamePrepareStart = true;

	/**
	 * game prepare stop constant
	 */
	public static final boolean isGamePrepareStop = false;

	/**
	 * game room start constant
	 */
	public static final boolean isRoomGameStart = true;

	/**
	 * game room stop constant
	 */
	public static final boolean isRoomGameStop = false;

	/**
	 * TicTacToc Game Draw Width 30
	 */
	public static final int nTicTacTocImageWidth = 30;

	/**
	 * TicTacToc Game Draw Height 30
	 */
	public static final int nTicTacTocImageHeight = 30;

	/**
	 * TicTacToc Game Width
	 */
	public static final int nTicTacTocBlockWidth = 12;

	/**
	 * TicTacToc Game Height
	 */
	public static final int nTicTacTocBlockHeight = 9;

	/**
	 * CatchMe Game Draw Width 30
	 */
	public static final int nCatchMeImageWidth = 30;

	/**
	 * CatchMe Game Draw Height 30
	 */
	public static final int nCatchMeImageHeight = 30;

	/**
	 * CatchMe Game Width
	 */
	public static final int nCatchMeBlockWidth = 12;

	/**
	 * CatchMe Game Height
	 */
	public static final int nCatchMeBlockHeight = 9;

	/**
	 * ID String Max Length
	 */
	public static final int nIDMaximumLenght = 7;

	/**
	 * PASSWORD String Max Length
	 */
	public static final int nPasswordMaximumLength = 7;

	/**
	 * setting asteroid game enemy number
	 */
	public static final int nSettingAsteroidNumber = 100;

	/**
	 * set game screen size width - meteor game, pangpang
	 */
	public static final int nGameAsteroidSceneWidth = 360;

	/**
	 * set game screen size height - meteor game, pangpang
	 */
	public static final int nGameAsteroidSceneHeight = 280;

	/**
	 * the Game Server IP address
	 */
	public static final String sConnectionServerAddress = "localhost";

	/**
	 * the Game Server Port Number
	 */
	public static final int nConnectionServerPortNumber = 5001;

	/**
	 * sprite sheet size width
	 */
	public static final float fSPRITEGAMEWIDTH = 320;

	/**
	 * sprite sheet size height
	 */
	public static final float fSPRITEGAMEHEIGHT = 480;

	/**
	 * encryption algorithm style
	 */
	
	public static final String ALGO = "AES";

	/**
	 * encryption key number 32bits
	 */
	public static final String sEncryptKey = "fe8025947de7cd71";
	
	/**
	 * encryption key number 64bits
	 */
	
	public static final String keyStr = "Z8LSq0wWwB5v+6YJzurcP463H3F12iZh74fDj4S74oUH4EONkiKb2FmiWUbtFh97GG/c/lbDE47mvw6j94yXxKHOpoqu6zpLKMKPcOoSppcVWb2q34qENBJkudXUh4MWcreondLmLL2UyydtFKuU9Sa5VgY/CzGaVGJABK2ZR94/";

	/**
	 * receving buffer size
	 */
	public static int nReceiveBufferSize = 10000;

	/**
	 * client version number for using updating check
	 */
	public static String clientVersion = "0.0.1v";

	/**
	 * building time for using debugging
	 */
	public static int nBuildingTimes;

	/**
	 * buildingVersion File Name
	 */
	public static final String sBuildingVersionFileName = "clientV.jrc";

	/**
	 * debugging mode
	 */
	public static final boolean isDevelopingMode = true;

	/**
	 * sending split protocol token
	 */
	public static final String sSenderSplitProtocolToken = "/ac";

	/**
	 * receiving split protocol token
	 */
	public static final String sReceiverSplitProtocolToken = "c/a";

	/**
	 * sender split multiple token
	 */
	public static final String sSenderSplitMultipleToken = "/k";

	/**
	 * receiver split multiple token
	 */
	public static final String sReceiverSplitMultipleToken = "k/";
	
    /**
     * pangapng Game information inner token
     */
	public static final String sPangPangPositionInformationWordToken = "a/a";
    
	/**
	 * pangpang Game information outer token
	 */
    public static final String sPangPangPositionCoordinationToken = "b/b";

    /**
     * version check on-off
     */
	public static boolean isClientVersionCheck = false;

	/**
	 * saving command list - size
	 */
	public static final int nMaximumSizeOfCommandsContainer = 20;
	
	/**
	 * pangpang Game enemy width
	 */
    public static final int nPangPangEnemyWidth = 8;
 
    /**
     * pangpang Game enemy height
     */
    public static final int nPangPangEnemyHeight = 6;
    
    /**
     * enemy die event cooltime
     */
    public static final int nEffectPoolSize = 20;
    
    /**
     * sound crash effect name
     */
    public static final String saCrashEffect = "shut";
    
    /**
     * music background path
     */
    public static final String saPangPangBGM = "/Asset/music.mp3";
    
	public static final boolean isTurnOnEncryption = false;
    
    /**
     * audio crash path
     */
    public static final String saPangPangCrashBGM = "/Asset/crash.wav";

}
