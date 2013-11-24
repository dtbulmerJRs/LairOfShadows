import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;

import javax.imageio.*;

public class Gam_alt extends JFrame {
	// variables that will be reused across the game
	int currentHealth;
	int currentAttackForce;
	int currentDefense;
	int currentSpecialStat;
	boolean desert = true;
	boolean forest = true;
	boolean swamp = true;
	char playerType;
	boolean playerAlive = true;
	char location;
	boolean subzone1 = true;
	boolean subzone2 = true;
	boolean subBoss=true;
	char enemyType;
	Randomizer Bob_the_AI=new Randomizer();
	Player player = null;
	Player Enemy=null;
	Player zoneSubBoss=null;
	Player theBoss=null;
	Image gImage = null;
	JPanel InitScreen = new JPanel();
	JPanel ClassChoiceScreen = new JPanel();
	JPanel gamePlay = new JPanel();
	JPanel westBlock = new JPanel();
	JPanel iPanel = new JPanel();
	JLabel iL = new JLabel();
	JPanel statPanel = new JPanel();
	JPanel dirButtonPanel = null;
	JTextArea hText = null;
	JTextArea afText = null;
	JTextArea dText = null;
	JTextArea gText = new JTextArea();
	JScrollPane gWall = null;
	FileReader fileReader;

	// The basic JFrame setup
	public Gam_alt() {
		this.setTitle("Lair Of Shadows");
		this.setSize(new Dimension(700, 500));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.getContentPane().setBackground(Color.black);
		this.setResizable(false);
		makeInitScreen();
	}

	// The start screen for the game
	public void makeInitScreen() {
		// creates a border layout for the JPanel InitScreen
		// containing the attributes wanted
		InitScreen.setLayout(new BorderLayout(4, 4));
		InitScreen.setForeground(Color.green);
		InitScreen.setBackground(Color.black);

		// The Text on the start screen
		JPanel initText = new JPanel();
		initText.setLayout(new GridLayout(3, 1, 1, 1));
		initText.setForeground(Color.green);
		initText.setBackground(Color.black);

		JLabel cName = new JLabel("Shadow Tech Enterprises");
		cName.setFont(new Font("Helevetica", Font.BOLD, 48));
		cName.setForeground(Color.green);
		cName.setBackground(Color.black);
		cName.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel pName = new JLabel("Presents:");
		pName.setFont(new Font("Helevetica", Font.BOLD, 24));
		pName.setForeground(Color.green);
		pName.setBackground(Color.black);
		pName.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel gName = new JLabel("Lair Of Shadows");
		gName.setFont(new Font("Helevetica", Font.BOLD, 48));
		gName.setForeground(Color.green);
		gName.setBackground(Color.black);
		gName.setHorizontalAlignment(SwingConstants.CENTER);

		initText.add(cName);
		initText.add(pName);
		initText.add(gName);

		// Initial Options
		ActionListener initListener = new initListener();
		JPanel initButtonPanel = new JPanel();
		initButtonPanel.setLayout(new GridLayout(2, 1, 4, 4));
		initButtonPanel.setForeground(Color.green);
		initButtonPanel.setBackground(Color.black);

		// Button for player choosing to start a game
		JButton startButton = new JButton("Start Game");
		startButton.setFont(new Font("Helevetica", Font.BOLD, 24));
		startButton.setForeground(Color.green);
		startButton.setBackground(Color.black);
		startButton.setHorizontalAlignment(SwingConstants.CENTER);
		startButton.addActionListener(initListener);

		// Button for players who don't want to play the game
		// Pressing the button leads to the game closing
		JButton quitButton = new JButton("Quit Game");
		quitButton.setFont(new Font("Helevetica", Font.BOLD, 24));
		quitButton.setForeground(Color.green);
		quitButton.setBackground(Color.black);
		quitButton.setHorizontalAlignment(SwingConstants.CENTER);
		quitButton.addActionListener(initListener);

		// buttons being added to the button panel
		initButtonPanel.add(startButton);
		initButtonPanel.add(quitButton);

		// Adding the text and button onto the JPanel
		InitScreen.add(initText, BorderLayout.NORTH);
		InitScreen.add(initButtonPanel, BorderLayout.CENTER);

		// Setting the JPanel onto the JFrame
		this.setContentPane(InitScreen);
	}
	
	/*The screen on which the player is
	 *  given the basic story/background
	 *  and chooses their character class 
	*/
	public void makeClassChoiceScreen() throws IOException {
		//Removes the Initial Screen
		this.remove(InitScreen);
		
		//Sets up the Class Choice Screen
		ClassChoiceScreen.setLayout(new BorderLayout(4, 4));
		ClassChoiceScreen.setForeground(Color.green);
		ClassChoiceScreen.setBackground(Color.black);

		//JTextArea on which the story will appear
		JTextArea classText = new JTextArea();
		//A file containing the story is 
		//read in and displayed on screen
		File fOpen = new File("Lair of Shadows Intro Story.txt");
		fileReader = new FileReader(fOpen);
		classText.read(fileReader, fOpen.toString());
		classText.append("\n"+"\n"+ "Choose your character class:\n");
		classText.append("Will you be a Warrior, a Mage, or a Rogue?\n");
		classText.append("Choose Wisely.\n");

		classText.setFont(new Font("Helevetica", Font.BOLD, 16));
		classText.setForeground(Color.green);
		classText.setBackground(Color.black);
		JScrollPane cWall = new JScrollPane(classText);
		cWall.setPreferredSize(new Dimension(490, 500));
		ClassChoiceScreen.add(cWall, BorderLayout.EAST);

		//Buttons that let the player choose
		//thier character class
		ActionListener classListener = new classListener();
		JPanel classButtonPanel = new JPanel();
		classButtonPanel.setLayout(new GridLayout(3, 1, 4, 4));
		classButtonPanel.setForeground(Color.green);
		classButtonPanel.setBackground(Color.black);

		JButton warButton = new JButton("Warrior");
		warButton.setFont(new Font("Helevetica", Font.BOLD, 24));
		warButton.setForeground(Color.green);
		warButton.setBackground(Color.black);
		warButton.setHorizontalAlignment(SwingConstants.CENTER);
		warButton.addActionListener(classListener);

		JButton mageButton = new JButton("Mage");
		mageButton.setFont(new Font("Helevetica", Font.BOLD, 24));
		mageButton.setForeground(Color.green);
		mageButton.setBackground(Color.black);
		mageButton.setHorizontalAlignment(SwingConstants.CENTER);
		mageButton.addActionListener(classListener);

		JButton rogButton = new JButton("Rogue");
		rogButton.setFont(new Font("Helevetica", Font.BOLD, 24));
		rogButton.setForeground(Color.green);
		rogButton.setBackground(Color.black);
		rogButton.setHorizontalAlignment(SwingConstants.CENTER);
		rogButton.addActionListener(classListener);

		classButtonPanel.add(warButton);
		classButtonPanel.add(mageButton);
		classButtonPanel.add(rogButton);
		classButtonPanel.setPreferredSize(new Dimension(200, 500));

		ClassChoiceScreen.add(classButtonPanel, BorderLayout.WEST);

		this.setContentPane(ClassChoiceScreen);
		this.validate();
		this.repaint();
	}

	//The main game screen from which players will
	//choose the zone they want to travel to
	//add Fianl Boss Stuff
	public void launchGame(char pT) throws IOException {
		this.getContentPane().removeAll();
		gamePlay.setLayout(new BorderLayout(4, 4));
		gamePlay.setForeground(Color.green);
		gamePlay.setBackground(Color.black);

		/*Depending on the player's class choice
		 * a specific file will open, informing the 
		 * player on the class's attributes and
		 * some basic information/back story
		*/
		File fOpen = null;

		switch (pT) {
		case 'w':
			fOpen = new File("Lair of Shadows Story Warrior.txt");
			break;
		case 'm':
			fOpen = new File("Lair of Shadows Story Mage.txt");
			break;
		case 'r':
			fOpen = new File("Lair of Shadows Story Rogue.txt");
			break;
		default:
			System.out.println("CATOSTROPHIC!");
		}
		;
		fileReader = new FileReader(fOpen);
		gText.read(fileReader, fOpen.toString());
		gText.setFont(new Font("Helevetica", Font.BOLD, 16));
		gText.setForeground(Color.green);
		gText.setBackground(Color.black);
		gWall = new JScrollPane(gText);
		gWall.setPreferredSize(new Dimension(490, 500));
		gamePlay.add(gWall, BorderLayout.EAST);

		//The information that will be displayed on
		//the Western area of the JFrame's Border Layout
		westBlock.setLayout(new BorderLayout(4, 4));
		westBlock.setForeground(Color.green);
		westBlock.setBackground(Color.black);
		
		//Initial image
		//This could be an image of the class character or possibly a game symbol
		//for now testing images are used
		iPanel.setBackground(Color.BLACK);
		iPanel.setPreferredSize(new Dimension(200, 300));
		try {
			gImage = ImageIO.read(new File("nirvash.jpg"));
		} catch (IOException e) {
			System.out.println("Check the Image SHIT!");
		}
		gImage = gImage.getScaledInstance(200, 300, 5);
		iL.setIcon(new ImageIcon(gImage));
		iL.setPreferredSize(new Dimension(200, 300));
		iL.setBackground(Color.BLACK);
		iPanel.add(iL);
		westBlock.add(iPanel, BorderLayout.NORTH);

		//The panel shows the player's stats
		statPanel.setForeground(Color.green);
		statPanel.setBackground(Color.black);
		statPanel.setLayout(new GridLayout(3, 1, 4, 4));
		
		currentHealth=player.getHealth();
		currentAttackForce=player.getAttackForce();
		currentDefense=player.getDefense();
		
		hText = new healthText(currentHealth);
		afText = new attackText(currentAttackForce);
		dText = new defenseText(currentDefense);

		statPanel.add(hText);
		statPanel.add(afText);
		statPanel.add(dText);
		statPanel.setPreferredSize(new Dimension(200, 100));
		westBlock.add(statPanel, BorderLayout.CENTER);

		//Creates a panel with the current zone choices available
		dirButtonPanel = new directionPanel(desert, forest, swamp);

		westBlock.add(dirButtonPanel, BorderLayout.SOUTH);
		gamePlay.add(westBlock, BorderLayout.WEST);
		gamePlay.setPreferredSize(new Dimension(200, 500));

		this.setContentPane(gamePlay);
		this.revalidate();
		this.repaint();

	}
	
	//Main play area for the Desert zone
	//add story
	//add SubBoss Stuff
	public void desertZone() throws IOException{
		if (subzone1){
			gText.append("\n"+"\n");
			String str = "";
			Scanner reader = new Scanner(new File("Lair of Shadows DesertSubZone Dunes.txt"));
			while (reader.hasNext()){
				str = reader.nextLine();	
				gText.append(str);
				gText.append("\n");
			}
			launchEvent();
		}
		else if(subzone2){
			gText.append("\n"+"\n");
			String str = "";
			Scanner reader = new Scanner(new File("Lair of Shadows DesertSubZone Canyon.txt"));
			while (reader.hasNext()){
				str = reader.nextLine();	
				gText.append(str);
				gText.append("\n");
			}
			launchEvent();
		}
		else if(subBoss){
			gText.append("\n"+"\n");
			String str = "";
			Scanner reader = new Scanner(new File("Lair of Shadows DesertBossZone Mountains.txt"));
			while (reader.hasNext()){
				str = reader.nextLine();	
				gText.append(str);
				gText.append("\n");
			}
			
		}
		else{
			subzone1=true;
			subzone2=true;
			launchGame(playerType);	
		}
	}
	
	//Main play area for the Forest zone
	//add story
	public void forestZone() throws IOException{
		if (subzone1){
			gText.append("\n"+"\n");
			String str = "";
			Scanner reader = new Scanner(new File("Lair of Shadows ForestSubZone Open Clearing.txt"));
			while (reader.hasNext()){
				str = reader.nextLine();	
				gText.append(str);
				gText.append("\n");
			}
			launchEvent();
		}
		else if(subzone2){
			gText.append("\n"+"\n");
			String str = "";
			Scanner reader = new Scanner(new File("Lair of Shadows ForestSubZone Cliff.txt"));
			while (reader.hasNext()){
				str = reader.nextLine();	
				gText.append(str);
				gText.append("\n");
			}
			launchEvent();
		}
		else if(subBoss){
			gText.append("\n"+"\n");
			String str = "";
			Scanner reader = new Scanner(new File("Lair of Shadows ForestBossZone Hollow Tree.txt"));
			while (reader.hasNext()){
				str = reader.nextLine();	
				gText.append(str);
				gText.append("\n");
			}
		}
		else{
			subzone1=true;
			subzone2=true;
			launchGame(playerType);
		}
	}
	
	//Main play area for the Swamp zone
	//add story
	public void swampZone() throws IOException{
		if (subzone1){
			gText.append("\n"+"\n");
			String str = "";
			Scanner reader = new Scanner(new File("Lair of Shadows SwampSubZone Lake.txt"));
			while (reader.hasNext()){
				str = reader.nextLine();	
				gText.append(str);
				gText.append("\n");
			}
			launchEvent();
		}
		else if(subzone2){
			gText.append("\n"+"\n");
			String str = "";
			Scanner reader = new Scanner(new File("Lair of Shadows SwampSubZone Dry Patch.txt"));
			while (reader.hasNext()){
				str = reader.nextLine();	
				gText.append(str);
				gText.append("\n");
			}
			launchEvent();
		}
		else if(subBoss){
			gText.append("\n"+"\n");
			String str = "";
			Scanner reader = new Scanner(new File("Lair of Shadows SwampBossZone Vine Covered Mass.txt"));
			while (reader.hasNext()){
				str = reader.nextLine();	
				gText.append(str);
				gText.append("\n");
			}
		}
		else{
			subzone1=true;
			subzone2=true;
			launchGame(playerType);	
		}
	}
	
	//could be specialized by zone
	public void launchEvent() {
		int event=Bob_the_AI.randomize(0, 3);
		switch(event){
		case 0:
			gText.append("\n"+"Enemy Encounter!"+"\n");
			createEnemy();
			launchCombat();
			break;
		case 1:
			gText.append("\n"+"Enemy Encounter!"+"\n");
			createEnemy();
			launchCombat();
			break;
		case 2:
			gText.append("\n"+"Enemy Encounter!"+"\n");
			createEnemy();
			launchCombat();
			break;
		case 3:
			//Random Encounter
			randomEcounter();
		default:
			System.out.println("\n"+"Catostraphic Disaster!!!!!!!"+"\n");
			break;
		}
	}
	
	public void randomEcounter(){
		int rdm=Bob_the_AI.randomize(0, 7);
		
		switch(rdm)
		{
		case 0:
			gText.append("You met a farmer who would like you to stay for Dinner.");
			player.adjHealth( (int) (player.getHealth()*.05));
			launchEvent();
			break;
		case 1:
			gText.append("A troll snuck into your camp over night and blunted your weapon!");
			player.adjAttackForce((int) (player.getAttackForce()*.05)*-1);
			launchEvent();
			break;
		case 2:
			gText.append("You found a health potion hidden behind a rock");
			player.adjHealth( (int) (player.getHealth()*.10));
			launchEvent();
			break;
		case 3:
			gText.append("A local armorer has heard tales of your struggle and wants to help. He fine tunes your weapon!");
			player.adjAttackForce((int) (player.getAttackForce()*.03));
			launchEvent();
			break;
		case 4:
			gText.append("You find and eat some bad mushrooms. You fall and ill and your Defense falls as a result");
			player.adjDefense((int) (player.getDefense()*.05)*-1);
			launchEvent();
			break;
		case 5:
			gText.append("Ralph the shield maker upgrades your defensive equipment in exchange for pint of local ale!");
			player.adjDefense((int) (player.getDefense()*.05));
			launchEvent();
			break;
		case 6:
			gText.append("A fairy appears out of nowhere and heals your wounds!");
			player.adjHealth( (int) (player.getHealth()*.025));
			launchEvent();
			break;
		case 7: 
			gText.append("A satyr at play throws a rock at your head while you sleep! Ouch!");
			player.adjHealth( (int) (player.getHealth()*.05)*-1);
			launchEvent();
			break;
		default:
			System.out.println("Your Random Event encounter code is broken");
			break;	
		}
	}
	
	public void createEnemy(){
		int e = Randomizer.randomize(0, 2);
		//Generate a Randomizer.random multiple between 25% and 100%
		int botStatsMultiplier = Randomizer.randomize(25, 75)/100;
		
		//The bot stats will be used as the initial stats for our bots
		//created by the following switch statement.
		int botHealth = botStatsMultiplier*player.getHealth();
		int botDefense = botStatsMultiplier*player.getDefense();
		int botAttackForce = botStatsMultiplier*player.getAttackForce();
		
		switch(e){
		case 0:
			//create a warrior enemy
			//Special Attribute Focus will be a product 1/2 the bots health
			Warrior warBot = new Warrior(botHealth, botAttackForce, botDefense, (int) (botHealth*.5));
			Enemy=new Warrior(warBot);
			enemyType='w';
			break;
		case 1:
			//create a rogue enemy
			//Special Attribute Agility will be a product 1/2 the bots health
			Rogue rogueBot = new Rogue(botHealth, botAttackForce, botDefense, (int) (botHealth*.5));
			Enemy=new Rogue(rogueBot);
			enemyType='r';
			break;
		case 2:
			//create a mage enemy
			//Special Attribute Mana will be a product 1/2 the bots health
			Mage mageBot = new Mage(botHealth, botAttackForce, botDefense, (int) (botHealth*.5));
			Enemy=new Mage(mageBot);
			enemyType='m';
			break;
		default:
			System.out.println("You should not reach this code in create an enemy");
		}
	}
	
	//Combat panel
	public void launchCombat(){
		switch(playerType){
		case 'w':
			dirButtonPanel=new warriorPanel();
			westBlock.add(dirButtonPanel, BorderLayout.SOUTH);
			revalidate();
			repaint();
			break;
		case 'm':
			dirButtonPanel=new magePanel();
			westBlock.add(dirButtonPanel, BorderLayout.SOUTH);
			revalidate();
			repaint();
			break;
		case 'r':
			dirButtonPanel=new roguePanel();
			westBlock.add(dirButtonPanel, BorderLayout.SOUTH);
			revalidate();
			repaint();
			break;
		default:
			System.out.println("Combat Error!");
		}
		/*The idea is that each button will lead to
		 * a new public void in which the enemies attack
		 * will be deduced and the combat will occur.
		 * The buttons available on the panel will depend
		 * on the special stat. After each round there will be a check to see if
		 * either the player or enemy has died. If the enemy dies the level is recalculated
		 * if the level is a milestone, player is sent back to their zones panel to see whether
		 * they go on to the next subzone or subboss, or to launchGame to choose a new zone.
		 * Should the player die the game is ended, what happens next is something we need to decide upon.
		 * If no one is dead we return here.
		*/
	}
	
	public void theCombat(){
		//A round of combat occurs
		//gText.append("\n"+"COMBAT!"+"\n");
		launchCombat();
	}
	class initListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String initChoice = event.getActionCommand();
			if (initChoice.equals("Start Game")) {
				try {
					makeClassChoiceScreen();
				} catch (FileNotFoundException e) {
					System.out.println("Catostrophic Disaster");
				} catch (IOException e) {
					System.out.println("Catostrophic Disaster");
				}
			} else if (initChoice.equals("Quit Game")) {
				setVisible(false);
				dispose();

			}
		}
	}

	class classListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String cChoice = event.getActionCommand();
			if (cChoice.equals("Warrior")) {
				player = new Warrior();
				playerType = 'w';
				try {
					launchGame(playerType);
				} catch (IOException e) {
					System.out.println("FAILURE!");
				}
			} else if (cChoice.equals("Mage")) {
				player = new Mage();
				playerType = 'm';
				try {
					launchGame(playerType);
				} catch (IOException e) {
					System.out.println("FAILURE!");
				}
			} else if (cChoice.equals("Rogue")) {
				player = new Rogue();
				playerType = 'r';
				try {
					launchGame(playerType);
				} catch (IOException e) {
					System.out.println("FAILURE!");
				}
			}
		}
	}

	class dirListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String cChoice = event.getActionCommand();
			if (cChoice.equals("Desert")) {
				File fOpen = new File("Lair of Shadows Story Desert.txt");
				try {
					fileReader = new FileReader(fOpen);
				} catch (FileNotFoundException e1) {
					System.out.println("DIR! ERROR! FILE");
				}
				try {
					gText.read(fileReader, fOpen.toString());
				} catch (IOException e) {
					System.out.println("DIR! ERROR! IO!");
				}
				location = 'd';
				westBlock.remove(dirButtonPanel);
				revalidate();
				repaint();
				try {
					desertZone();
				} catch (IOException e) {
					System.out.println("Check Desert Zone Stuff!");
				}
			} else if (cChoice.equals("Forest")) {
				File fOpen = new File("Lair of Shadows Story Forest.txt");
				try {
					fileReader = new FileReader(fOpen);
				} catch (FileNotFoundException e1) {
					System.out.println("DIR! ERROR! FILE");
				}
				try {
					gText.read(fileReader, fOpen.toString());
				} catch (IOException e) {
					System.out.println("DIR! ERROR! IO!");
				}
				location = 'f';
				westBlock.remove(dirButtonPanel);
				revalidate();
				repaint();
				try {
					forestZone();
				} catch (IOException e) {
					System.out.println("Check Forest Zone Stuff!");
				}
			} else if (cChoice.equals("Swamp")) {
				File fOpen = new File("Lair of Shadows Story Swamp.txt");
				try {
					fileReader = new FileReader(fOpen);
				} catch (FileNotFoundException e1) {
					System.out.println("DIR! ERROR! FILE");
				}
				try {
					gText.read(fileReader, fOpen.toString());
				} catch (IOException e) {
					System.out.println("DIR! ERROR! IO!");
				}
				location = 's';
				westBlock.remove(dirButtonPanel);
				revalidate();
				repaint();
				try {
					swampZone();
				} catch (IOException e) {
					System.out.println("Check Swamp Zone Stuff!");
				}
			}
		}
	}

	class directionPanel extends JPanel {
		public directionPanel(boolean d, boolean f, boolean s) {
			ActionListener dirListener = new dirListener();
			this.setLayout(new GridLayout(3, 1, 4, 4));
			this.setForeground(Color.green);
			this.setBackground(Color.black);

			if (d & f & s) {
				JButton dButton = new JButton("Desert");
				dButton.setFont(new Font("Helevetica", Font.BOLD, 12));
				dButton.setForeground(Color.green);
				dButton.setBackground(Color.black);
				dButton.setHorizontalAlignment(SwingConstants.CENTER);
				dButton.addActionListener(dirListener);

				JButton fButton = new JButton("Forest");
				fButton.setFont(new Font("Helevetica", Font.BOLD, 12));
				fButton.setForeground(Color.green);
				fButton.setBackground(Color.black);
				fButton.setHorizontalAlignment(SwingConstants.CENTER);
				fButton.addActionListener(dirListener);

				JButton lButton = new JButton("Swamp");
				lButton.setFont(new Font("Helevetica", Font.BOLD, 12));
				lButton.setForeground(Color.green);
				lButton.setBackground(Color.black);
				lButton.setHorizontalAlignment(SwingConstants.CENTER);
				lButton.addActionListener(dirListener);

				this.add(dButton);
				this.add(fButton);
				this.add(lButton);
				this.setPreferredSize(new Dimension(200, 100));
			} else if (d & f) {
				JButton dButton = new JButton("Desert");
				dButton.setFont(new Font("Helevetica", Font.BOLD, 12));
				dButton.setForeground(Color.green);
				dButton.setBackground(Color.black);
				dButton.setHorizontalAlignment(SwingConstants.CENTER);
				dButton.addActionListener(dirListener);

				JButton fButton = new JButton("Forest");
				fButton.setFont(new Font("Helevetica", Font.BOLD, 12));
				fButton.setForeground(Color.green);
				fButton.setBackground(Color.black);
				fButton.setHorizontalAlignment(SwingConstants.CENTER);
				fButton.addActionListener(dirListener);

				this.add(dButton);
				this.add(fButton);
				this.setPreferredSize(new Dimension(200, 100));
			} else if (d & s) {
				JButton dButton = new JButton("Desert");
				dButton.setFont(new Font("Helevetica", Font.BOLD, 12));
				dButton.setForeground(Color.green);
				dButton.setBackground(Color.black);
				dButton.setHorizontalAlignment(SwingConstants.CENTER);
				dButton.addActionListener(dirListener);

				JButton lButton = new JButton("Swamp");
				lButton.setFont(new Font("Helevetica", Font.BOLD, 12));
				lButton.setForeground(Color.green);
				lButton.setBackground(Color.black);
				lButton.setHorizontalAlignment(SwingConstants.CENTER);
				lButton.addActionListener(dirListener);

				this.add(dButton);
				this.add(lButton);
				this.setPreferredSize(new Dimension(200, 100));
			} else if (f & s) {
				JButton fButton = new JButton("Forest");
				fButton.setFont(new Font("Helevetica", Font.BOLD, 12));
				fButton.setForeground(Color.green);
				fButton.setBackground(Color.black);
				fButton.setHorizontalAlignment(SwingConstants.CENTER);
				fButton.addActionListener(dirListener);

				JButton lButton = new JButton("Swamp");
				lButton.setFont(new Font("Helevetica", Font.BOLD, 12));
				lButton.setForeground(Color.green);
				lButton.setBackground(Color.black);
				lButton.setHorizontalAlignment(SwingConstants.CENTER);
				lButton.addActionListener(dirListener);

				this.add(fButton);
				this.add(lButton);
				this.setPreferredSize(new Dimension(200, 100));
			} else if (d) {
				JButton dButton = new JButton("Desert");
				dButton.setFont(new Font("Helevetica", Font.BOLD, 12));
				dButton.setForeground(Color.green);
				dButton.setBackground(Color.black);
				dButton.setHorizontalAlignment(SwingConstants.CENTER);
				dButton.addActionListener(dirListener);
				this.add(dButton);
				this.setPreferredSize(new Dimension(200, 100));
			} else if (f) {
				JButton fButton = new JButton("Forest");
				fButton.setFont(new Font("Helevetica", Font.BOLD, 12));
				fButton.setForeground(Color.green);
				fButton.setBackground(Color.black);
				fButton.setHorizontalAlignment(SwingConstants.CENTER);
				fButton.addActionListener(dirListener);

				this.add(fButton);
				this.setPreferredSize(new Dimension(200, 100));
			} else if (s) {
				JButton lButton = new JButton("Swamp");
				lButton.setFont(new Font("Helevetica", Font.BOLD, 12));
				lButton.setForeground(Color.green);
				lButton.setBackground(Color.black);
				lButton.setHorizontalAlignment(SwingConstants.CENTER);
				lButton.addActionListener(dirListener);

				this.add(lButton);
				this.setPreferredSize(new Dimension(200, 100));
			}
		}
	}


	class healthText extends JTextArea {
		public healthText() {
			this.setText("Health: ");
			this.setFont(new Font("Helevetica", Font.BOLD, 12));
			this.setForeground(Color.green);
			this.setBackground(Color.black);
		}
		
		public healthText(int h){
			this.setText("Health: "+h);
			this.setFont(new Font("Helevetica", Font.BOLD, 12));
			this.setForeground(Color.green);
			this.setBackground(Color.black);
		}
		
		public void updateText(int h) {
			this.setText("Health: "+h);
		}
	}

	class attackText extends JTextArea {
		public attackText() {
			this.setText("Attack Force: ");
			this.setFont(new Font("Helevetica", Font.BOLD, 12));
			this.setForeground(Color.green);
			this.setBackground(Color.black);
		}
		public attackText(int a){
			this.setText("Attack Force: "+a);
			this.setFont(new Font("Helevetica", Font.BOLD, 12));
			this.setForeground(Color.green);
			this.setBackground(Color.black);
		}

		public void updateText(int a) {
			this.setText("Attack Force: "+a);
		}
	}

	class defenseText extends JTextArea {
		public defenseText() {
			this.setText("Defense: ");
			this.setFont(new Font("Helevetica", Font.BOLD, 12));
			this.setForeground(Color.green);
			this.setBackground(Color.black);
		}
		public defenseText(int d){
			this.setText("Defense: "+d);
			this.setFont(new Font("Helevetica", Font.BOLD, 12));
			this.setForeground(Color.green);
			this.setBackground(Color.black);
		}

		public void updateText(int d) {
			this.setText("Defense: "+d);
		}
	}

	class combatListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String cChoice = event.getActionCommand();
			if (cChoice.equals("Basic Attack")) {
				theCombat();
			} 
			else if (cChoice.equals("Power Strike")) {
				theCombat();
			} 
			else if (cChoice.equals("Block")) {
				theCombat();
			}
			else if(cChoice.equals("Critical Strike")){
				theCombat();
			}
			else if(cChoice.equals("Disarm")){
				theCombat();
			}
			else if(cChoice.equals("Cast")){
				theCombat();
			}
			else if(cChoice.equals("Heal")){
				theCombat();
			}
			else if(cChoice.equals("Run Away")){
				//run away
			}
		}
	}
	
	class warriorPanel extends JPanel{
		public warriorPanel(){
			ActionListener combatListener = new combatListener();
			this.setLayout(new GridLayout(4, 1, 4, 4));
			this.setForeground(Color.green);
			this.setBackground(Color.black);
			
			JButton baButton = new JButton("Basic Attack");
			baButton.setFont(new Font("Helevetica", Font.BOLD, 12));
			baButton.setForeground(Color.green);
			baButton.setBackground(Color.black);
			baButton.setHorizontalAlignment(SwingConstants.CENTER);
			baButton.addActionListener(combatListener);
			
			JButton pButton = new JButton("Power Strike");
			pButton.setFont(new Font("Helevetica", Font.BOLD, 12));
			pButton.setForeground(Color.green);
			pButton.setBackground(Color.black);
			pButton.setHorizontalAlignment(SwingConstants.CENTER);
			pButton.addActionListener(combatListener);
			
			JButton blButton = new JButton("Block");
			blButton.setFont(new Font("Helevetica", Font.BOLD, 12));
			blButton.setForeground(Color.green);
			blButton.setBackground(Color.black);
			blButton.setHorizontalAlignment(SwingConstants.CENTER);
			blButton.addActionListener(combatListener);
			
			JButton rButton = new JButton("Run Away");
			rButton.setFont(new Font("Helevetica", Font.BOLD, 12));
			rButton.setForeground(Color.green);
			rButton.setBackground(Color.black);
			rButton.setHorizontalAlignment(SwingConstants.CENTER);
			rButton.addActionListener(combatListener);
			
			this.add(baButton);
			this.add(pButton);
			this.add(blButton);
			this.add(rButton);
			this.setPreferredSize(new Dimension(200, 100));
		}
	}
	
	class magePanel extends JPanel{
		public magePanel(){
			ActionListener combatListener = new combatListener();
			this.setLayout(new GridLayout(4, 1, 4, 4));
			this.setForeground(Color.green);
			this.setBackground(Color.black);
			
			JButton baButton = new JButton("Basic Attack");
			baButton.setFont(new Font("Helevetica", Font.BOLD, 12));
			baButton.setForeground(Color.green);
			baButton.setBackground(Color.black);
			baButton.setHorizontalAlignment(SwingConstants.CENTER);
			baButton.addActionListener(combatListener);
			
			JButton pButton = new JButton("Cast");
			pButton.setFont(new Font("Helevetica", Font.BOLD, 12));
			pButton.setForeground(Color.green);
			pButton.setBackground(Color.black);
			pButton.setHorizontalAlignment(SwingConstants.CENTER);
			pButton.addActionListener(combatListener);
			
			JButton blButton = new JButton("Heal");
			blButton.setFont(new Font("Helevetica", Font.BOLD, 12));
			blButton.setForeground(Color.green);
			blButton.setBackground(Color.black);
			blButton.setHorizontalAlignment(SwingConstants.CENTER);
			blButton.addActionListener(combatListener);
			
			JButton rButton = new JButton("Run Away");
			rButton.setFont(new Font("Helevetica", Font.BOLD, 12));
			rButton.setForeground(Color.green);
			rButton.setBackground(Color.black);
			rButton.setHorizontalAlignment(SwingConstants.CENTER);
			rButton.addActionListener(combatListener);
			
			this.add(baButton);
			this.add(pButton);
			this.add(blButton);
			this.add(rButton);
			this.setPreferredSize(new Dimension(200, 100));
		}
	}
	
	class roguePanel extends JPanel{
		public roguePanel(){
			ActionListener combatListener = new combatListener();
			this.setLayout(new GridLayout(4, 1, 4, 4));
			this.setForeground(Color.green);
			this.setBackground(Color.black);
			
			JButton baButton = new JButton("Basic Attack");
			baButton.setFont(new Font("Helevetica", Font.BOLD, 12));
			baButton.setForeground(Color.green);
			baButton.setBackground(Color.black);
			baButton.setHorizontalAlignment(SwingConstants.CENTER);
			baButton.addActionListener(combatListener);
			
			JButton pButton = new JButton("Crtical Strike");
			pButton.setFont(new Font("Helevetica", Font.BOLD, 12));
			pButton.setForeground(Color.green);
			pButton.setBackground(Color.black);
			pButton.setHorizontalAlignment(SwingConstants.CENTER);
			pButton.addActionListener(combatListener);
			
			JButton blButton = new JButton("Disarm");
			blButton.setFont(new Font("Helevetica", Font.BOLD, 12));
			blButton.setForeground(Color.green);
			blButton.setBackground(Color.black);
			blButton.setHorizontalAlignment(SwingConstants.CENTER);
			blButton.addActionListener(combatListener);
			
			JButton rButton = new JButton("Run Away");
			rButton.setFont(new Font("Helevetica", Font.BOLD, 12));
			rButton.setForeground(Color.green);
			rButton.setBackground(Color.black);
			rButton.setHorizontalAlignment(SwingConstants.CENTER);
			rButton.addActionListener(combatListener);
			
			this.add(baButton);
			this.add(pButton);
			this.add(blButton);
			this.add(rButton);
			this.setPreferredSize(new Dimension(200, 100));
		}
	}
}
