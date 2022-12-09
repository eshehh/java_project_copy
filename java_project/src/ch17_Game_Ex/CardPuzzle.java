package ch17_Game_Ex;

import java.awt.*;


import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;

import ch17_Game_Ex.RoundedButton;


public class CardPuzzle extends JFrame{

	File path = new File("");   // 게임 경로 찾기
	String userId ;			// 게임유저 아이디 받아오기
	
	int cardrandom[] = new int [20];		// 카드 랜덤번호가 들어갈 배열

	
	// 패널 1 메뉴부분
	JPanel panel1;		
	int count=20;
	JLabel labelCount;			// 패널1의 남은개수
	int sec=0;
	JLabel labelTimer; 		// 시간초 보여줄 패널1의 라벨
	Timer timerSec = new Timer();		// 패널1에 보여줄 라벨의 타이머 초기화
	RoundedButton buttonStart;	  		// 게임 시작 버튼

	
	// 패널 2 게임구현부분
	JPanel panel2;		
	ImageIcon imageBack ;			// 뒷면 이미지 저장할 아이콘
	ImageIcon imageIcon[]=new ImageIcon[20] ;			// 앞면 이미지 저장할 20개 아이콘들
	int startCheck;		// 새 게임버튼 안누르고 그림 누를경우엔 0, 새게임 누르고 패누를땐 1
	JLabel labelImage[]=new JLabel[20] ;		// 이미지들을 올려둘 버튼 20개
	Timer timerMix = new Timer();		// 패를 섞을때 속도 조절위한 타이머 초기화
	Timer timerHide = new Timer();		// 패를 뒤집기전에 패를 몇초간 보여줄지 타이머
	Timer timerCardCheck = new Timer();		// 패 2개를 뒤집고나서 틀렸을 경우 몇초간 보여줄지 타이머
	JLabel labelConfirmedCheck;   	// 2개 패 찾기를 성공한것을 누른건지 확인하기위한버튼
	JLabel labelSelectedFirst;		// 처음 선택된 버튼
	JLabel labelSelectedSecond;		// 두번째 선택된 버튼
	int selectedTwoCardCheck;		// 버튼이 몇번 눌렷는지 체크하기
	int firstCardNumber;		// 첫번째 눌린 버튼에서 배열의 카드번호 저장하기위해
	int secondCardNumber;	// 두번째 눌린 버튼에서 배열의 카드번호 저장하기위해
	int selectedImageNumber ;		// 몇번째 버튼인지 확인하기 위한 눌려진 버튼의 번호 저장
	ImageIcon selectedImage ;		// 눌려진 이미지 보여주기위해
	

	// 결과 팝업창
	JDialog dialogResult;
	JLabel labelResultText;
	JLabel labelResultTime;
	JPanel buttonPanel;
	RoundedButton buttonResultOK;  // 확인버튼
	RoundedButton buttonResultEnd;  // 종료버튼
	
	//int cardPuzzleScore=0;
	
	
	
	public CardPuzzle(){					// 초기 생성자
	
		super("CardPuzzle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 윈도우 창 종료시 프로세스 까지 닫아줌
		
		panel1 = new JPanel1();
		panel2 = new JPanel2();
		
		setBackground(Color.white);  // 배경색
		setLayout(new BorderLayout());
		startCheck=0;

		// 결과 파업창 : 확인버튼
		buttonResultOK = new RoundedButton("확인");
		buttonResultOK.setFont(new Font("맑은 고딕",Font.BOLD , 25));
		buttonResultOK.addActionListener(new MyActionListenerResult());  // 이벤트 메소드 호출
		//buttonResultOK.setPreferredSize(new Dimension(170, 90));  // 레이아웃을 그대로 두고 버튼의 크기 변경
		//buttonResultOK.addActionListener(new EventHandlerResultOK());
		
		// 결과 파업창 : 종료버튼
		buttonResultEnd = new RoundedButton("종료");
		buttonResultEnd.setFont(new Font("맑은 고딕",Font.BOLD , 25));
		buttonResultEnd.addActionListener(new MyActionListenerResult());  // 이벤트 메소드 호출
		
		// 결과 파업창 : 버튼의 panel
		buttonPanel = new JPanel();
		buttonPanel.add(buttonResultOK);
		buttonPanel.add(buttonResultEnd);
		
		// 결과 파업창 
		dialogResult = new JDialog(this, "게임 결과");
		dialogResult.setLayout(new GridLayout(3,1));
		//dialogResult.setBounds(650, 500, 300, 200);  //절대 위치와 크기 고정 (x좌표, y좌표, 가로크기, 세로크기)
		dialogResult.setSize(300, 200);
		dialogResult.setLocationRelativeTo(null);  // dialogResult를 화면에 맞게 중앙정렬
		dialogResult.add(labelResultText);
		dialogResult.add(labelResultTime);
		dialogResult.add(buttonPanel);
		
		
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);

		setSize(600,700);  // 창 크기 지정
		setLocationRelativeTo(null);  // 화면 크기에 맞게 JFrame 중앙정렬
		setVisible(true);    
	}
	
	
	class JPanel1 extends JPanel{		// 시작버튼, 개수 라벨, 시간 라벨
		
		public JPanel1(){
			
				setBackground(Color.white);
	            setLayout(new GridLayout(1,3,30,5));   //상단 패널 레이아웃 설정  // 1행 3열, 행 간격 : 30, 열 간격 : 5
	        
	            buttonStart = new RoundedButton("New Game!!");

	        	labelTimer = new JLabel("  "+sec+" 초");	
	            labelTimer.setFont(new Font("맑은 고딕",Font.BOLD , 20));  //글자스타일
	    		labelTimer.setHorizontalAlignment(NORMAL);
	    		
	    		labelCount = new JLabel("남은 개수 :  "+count + "개 ");  
	    		labelCount.setFont(new Font("맑은 고딕",Font.BOLD , 20)); //글자스타일
	            labelCount.setHorizontalAlignment(NORMAL); // 위치
	            
	    		labelResultText = new JLabel();
	    		labelResultText.setHorizontalAlignment(NORMAL);
	    		
	    		labelResultTime = new JLabel();
	    		labelResultTime.setHorizontalAlignment(NORMAL);
	    		
	            
	        	add(labelCount);
	            add(buttonStart);
	            add(labelTimer);

	            buttonStart.addActionListener(new MyActionListenerNewGame());  // 시작버튼 클릭 시 이벤트
	            
		}			
	}
	// 패널2	
	
	class JPanel2 extends JPanel{		// 게임 구현 패널
		
		public JPanel2(){
		
			setBackground(Color.white);
			setLayout(new GridLayout(4,5));  // 4행5열을 가지는 레이아웃 작성, 가로세로 여백을 준다.
			
			mixNumber();		// 패섞기
			setImage();			// 이미지 받아오기
			setButtonFirstImage();		// 버튼에 이미지 씌우기
			setButtonName();			// 버튼에 각각 이름 붙이기
			
			for(int i =0; i<20;i++){
				labelImage[i].addMouseListener(new jjacMouseListener()); // 이미지를 올릴 라벨의 생성 및 배치
			}
			
			for(int i =0; i<20;i++){
				add(labelImage[i]);
			}
		}
		
	}
	// 패널2
	
	class MyActionListenerResult implements ActionListener {  // 게임 결과 창 확인 버튼, 종료 버튼
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == buttonResultOK) {  // getSource()메서드는 이벤트를 발생시킨 이벤트 소스 객체를 얻어온다.
				dialogResult.dispose();  // 결과창 닫기
				//new CardPuzzle();
				
			}else if(e.getSource() == buttonResultEnd) {
				System.exit(0);  // 게임창까지 닫기
			}
		}
		
	}
	

	class MyActionListenerNewGame implements ActionListener {    //  게임 시작버튼
        @Override
        public void actionPerformed(ActionEvent e) {
        	
        	setButtonName(); // 버튼에 이름주기
        	startCheck=1; 
        	sec = 0;
        	count = 20;
    		labelTimer.setText("  "+sec+" 초");			// 라벨 0으로 초기화
    		labelCount.setText("남은 개수 :  "+count + "개 ");			// 라벨 20으로 초기화
    		
        	timerMix.cancel();	
    	  	timerMix = new Timer();		// 타이머 객체 초기화
    	  	timerHide.cancel();	
    		timerHide = new Timer();		// 타이머 객체 초기화
          	timerSec.cancel();				// 타이머 객체 없애기
    	  	timerSec = new Timer();    // 타이머 객체 초기화
    	  	
        	timerMix.scheduleAtFixedRate(new TimerTask() { 		// 패 섞는 모션  
        		int i=0;
				public void run() {
					mixNumber();  // 카드섞기
					setImage();
					setButtonResetImage();
					i=i+1;
					if(i==20)timerMix.cancel();			// 20번 섞었으면 타이머 종료시키기
				}
			}, 0,50);			// 0초후 실행해서 0.05초간격으로 섞기
 			
        	
    		timerHide.scheduleAtFixedRate(new TimerTask() {		// 패 2초 보여준 후 뒤집는 모션
    			public void run() {
    				hideButtonImage();  // 버튼 그림 뒷면 보이기
    				timerHide.cancel();  // 그림 뒷면이 보이면 타이머 종료시키기
    			}
    		}, 2000, 1); 		// 2초후 실행
        	

        	timerSec.scheduleAtFixedRate(new TimerTask() { 		//  게임시작후 시간초
        
				public  void run() {
					labelTimer.setText("  "+sec+" 초");	
					sec=sec+1;
				}
			}, 2000,1000);		// 2초후 1초 간격으로 실행
        
        }
     }
	// 게임 시작버튼
 	
	private class jjacMouseListener implements MouseListener{		// 두개 카드 확인 및 결과 표시
		public void mouseClicked(java.awt.event.MouseEvent e) {		
		}
		public void mouseEntered(java.awt.event.MouseEvent e) {
		}
		public void mouseExited(java.awt.event.MouseEvent e) {	
		}
		public void mousePressed(java.awt.event.MouseEvent e) {		// 마우스 버튼이 눌리면
			labelConfirmedCheck = ((JLabel)e.getSource());
        	if(labelConfirmedCheck.getName()=="checked"){    // 눌린 버튼이 checked이면 성공한버튼 눌린거면 실행안하기
            	selectedTwoCardCheck = 0;    // 성공한 패를 눌렀으므로 카운트 다시 0으로
            	
        	}else if(startCheck==1 && (selectedTwoCardCheck==0 || selectedTwoCardCheck==1)){  
        		// 카드가 뒤집어 졌을때만 실행하기위해

        			selectedTwoCardCheck += 1;		 // 카드 선택 될때마다 카운트 + 1
        	
        			// 첫번째 눌린 카드 번호
        			if(selectedTwoCardCheck==1){		
        				
      	
        				labelSelectedFirst=((JLabel)e.getSource());  // 첫번째 눌린 버튼객체 가져오기
        				selectedImageNumber =Integer.parseInt(labelSelectedFirst.getName()) -1;  // 이름이 string 이므로 int로 변환 -> 배열에 들어가 있기에 -1을 해줌(인덱스는 0번부터 시작)
        				selectedImage = new ImageIcon(path +"pic/image/"+cardrandom[selectedImageNumber]+".png");  
        				labelSelectedFirst.setIcon(selectedImage);     // 버튼 눌려진 이미지 보여주기
        		 
        				firstCardNumber = cardrandom[selectedImageNumber];	// 카드 번호가 10이하이면 그냥 저장
        				if(cardrandom[selectedImageNumber]>10)			// 카드 번호가 10보다 크면 -10
        					firstCardNumber = cardrandom[selectedImageNumber]-10; 
        			}// 첫번째 눌린 카드 번호

        			// 두번째 눌린 카드 번호
    				if(labelConfirmedCheck.getName()==labelSelectedFirst.getName()){		// 두번째 클릭이 처음클릭한 카드를 또 선택했으면 카운트0
    					selectedTwoCardCheck = 1;
    				}else if(selectedTwoCardCheck==2	){			
        				labelSelectedSecond=((JLabel)e.getSource());  // 첫번째 눌린 버튼객체 가져오기
        				selectedImageNumber =Integer.parseInt(labelSelectedSecond.getName()) -1;  // 이름이 string 이므로 int로 변환
        				selectedImage = new ImageIcon(path +"pic/image/"+cardrandom[selectedImageNumber]+".png"); 
        				labelSelectedSecond.setIcon(selectedImage);     // 버튼 눌려진 이미지 보여주기
        				
        				secondCardNumber = cardrandom[selectedImageNumber];	// 카드 번호가 10이하면 그냥 저장
        				if(cardrandom[selectedImageNumber]>10)				// 카드 번호가 10보다 크면 -10
        					secondCardNumber = cardrandom[selectedImageNumber]-10; 
        				
        				if(firstCardNumber==secondCardNumber ){   	// 첫번째 두번째 선택된 카드 비교
        					selectedTwoCardCheck =0;		// 카드 2선택되면 다시  초기화
        					labelSelectedFirst.setName("checked"); 				// 성공이므로 버튼 이름 checked으로 해서 선택못하게 하기
        					labelSelectedSecond.setName("checked"); // 성공이므로 버튼 이름 checked으로 해서 선택못하게 하기
        					count = count - 2;
        					labelCount.setText("남은 개수 :  "+count + "개 ");	
        			 
        					int end=0;  // 패 몇개 맞췄는지 확인하기 
        					for(int i=0; i<20; i++){		 // 패 몇개 맞췄는지 확인하기 
        						if((labelImage[i].getName()).equals("checked")){
        							end=end+1;
        							if (end==20){		// 20개 전부 맞았는지 체크
        								
        								timerSec.cancel();		//  타이머 정지
        								labelTimer.setText("  "+sec+" 초");	
        							/*	if(sec>100){  // 100초 넘도록 못 풀면 100초라고 본다 점수는 고로 0점
        									sec=100;
        								}
        								cardPuzzleScore = 100-sec;
        								labelScore.setText("점수 :  "+cardPuzzleScore+" 점"); */
        								
        								dialogResult();
        								break;
        							}
        						}
        					}
        					
        				}else{
        					timerCardCheck = new Timer();
        					timerCardCheck.scheduleAtFixedRate(new TimerTask() { 	//첫번째 두번째 카드비교후 틀리면 몇초보여주기
        	
        						public void run() {
        							selectedTwoCardCheck =0;		// 카드 2선택되면 다시  초기화 , 카드가 뒷면보일때까지 다른것 선택못하게하기
        							labelSelectedFirst.setIcon(imageBack); 				// 틀렸으므로 첫번째 카드 다시 뒤집기
        							labelSelectedSecond.setIcon(imageBack); 			// 틀렸으므로 두번째 카드 다시 뒤집기
        							timerCardCheck.cancel();				// 뒤집고 타이머 종료
        						}
        					}, 300,1);				// 0.3초후 카드 뒷면 보이게 하기.
        				} 
        			}// 두번째 눌린 카드 번호		
        	}
			
		}
		public void mouseReleased(java.awt.event.MouseEvent e) {
		}
	}
	// 두개 카드 확인 및 결과 표시

	/*class EventHandlerResultOK implements ActionListener{    // 게임결과확인버튼으로 DB에 시간 저장하기  // 전부 주석처리해도된다.
		
		public void actionPerformed(ActionEvent e){	
			
			//dialogResult.dispose();   // 확인버튼 있는 다이얼로그창 없애기

			int ReceivedDBTime=0;
			
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";   // 자신의 오라클 아이피로해야한다.
			String userid = "scott";
			String passwd = "tiger";

	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;   // 리턴받아 사용할 객체 생성
	     
   	        String querySelect = "select nickname, time from USER_INFO where nickname=?"; 	 
										// SUTDASCORE 검색   // 자신의 오라클DB에 맞게 설정해야한다.
	        
	        try{													// select
	            Class.forName(driver);
	            con = DriverManager.getConnection(url, userid, passwd);
	            pstmt = con.prepareStatement(querySelect);
	           
	            rs = pstmt.executeQuery(); // 리턴받아온 쿼리 내용을 객체생성
	            while(rs.next()){
	                ReceivedDBTime = rs.getInt("sec");
	            }
	            
	        }catch(Exception e1){
	            System.out.println(e1.getMessage());
	        }finally{
	            try {
	                rs.close(); 
	                pstmt.close(); 
	                con.close();   // 객체 생성한 반대 순으로 사용한 객체는 닫아준다.
	            } catch (Exception e2) {}
	        }
  

		}
	} */
	//  EventHandlerResultOK
	
	
	public void mixNumber(){			// 카드 섞기
		
		int i=0;
		int rand=0;
		while(true){
			
			rand =  (int)(Math.random()*20+1);
			cardrandom[i] =rand;

	  aa : for(int j=0; j<20; j++){			
				if(j==i)
					break aa ;
				if(cardrandom[j]==rand){
					i=i-1 ;
					}
			}i=i+1;
			if(i==-1)i=0;
			if(i==20)break;
		}
	
	}
	
	public void setImage(){				// 이미지객체에 그림 가져오기
		
		imageBack = new ImageIcon(path +"pic/image/0.png");
		for ( int i = 0; i<20; i++){
			 imageIcon[i]= new ImageIcon(path +"pic/image/"+cardrandom[i]+".png");
		}
	}

	public void setButtonFirstImage(){		// 버튼 설정 및 처음 가져온 그림 보여주기
		
		for ( int i = 0; i<20; i++){
			 labelImage[i] =  new JLabel(imageIcon[i]);
			 labelImage[i].setBackground(Color.white); 	// 배경색
			 labelImage[i].setBorder(new LineBorder(Color.white));
		}	
}

	public void setButtonResetImage(){		// 버튼에 리셋된 이미지 재설정하기

		for ( int i = 0; i<20; i++){
			 labelImage[i].setIcon(imageIcon[i]);
		}
	}
	
	public void setButtonName(){		// 버튼에 이름주기
		
		for ( int i = 0; i<20; i++){
			 labelImage[i].setName(Integer.toString(i+1));
		}		
	}

	public void hideButtonImage(){		// 버튼 그림 뒷면 보이기
		
		for ( int i = 0; i<20; i++){
			 labelImage[i].setIcon(imageBack);
		}
		
	}
	
	
	void dialogResult(){		// 다이얼로그 보여주기
		
			labelResultText.setText("Congratulations~!!");
			Font font = new Font("맑은 고딕", Font.BOLD, 20);
			labelResultText.setFont(font);
			
			labelResultTime.setText(sec+"초 걸렸습니다.");
			Font font2 = new Font("맑은 고딕", Font.BOLD, 15);
			labelResultTime.setFont(font2);
			
			dialogResult.setVisible(true);

	} 
	
}
