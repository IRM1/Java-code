import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements KeyListener { // makes Jpannel class and its differnt fileds
    private JLabel basket;
    private JLabel ball;
    private ImageIcon iconbasket;
    private ImageIcon iconball;
    private int labelXbasket;
    private int labelYbasket;
    private int labelXball;
    private int labelYball;
    private int ScoreCounter;
    private JLabel Score;
    private int speed;
    private int levelcounter;
    private JLabel levelshow;
    private int level;

    public GamePanel() { // intiallizes those fileds 
        speed = 1;
        levelcounter = 0;
        labelYball = 0;
        level = 0;
        Random rng = new Random();
        setLayout(null);
        setBackground(Color.GREEN); //set background color of jpannel

        basket = new JLabel();//makes a jlabel basket
        iconbasket = new ImageIcon("src\\basket.png"); //makes an Imangicon of image which file path is mentioned
        basket.setIcon(iconbasket); //sets the jlabel basket to iconbasket
        labelXbasket = 350;
        labelYbasket = 700;
        basket.setBounds(labelXbasket, labelYbasket, 100, 100); // decides what will be intialle position height and width of basket
        add(basket); //adding it to pannel
        addKeyListener(this); //this is adding action listner to Pannel of type keylistner which takes input of keyborad and perfom a differnt action(watched a youtube video before this )
        setFocusable(true);

        ball = new JLabel(); //same like basket label ball label
        iconball = new ImageIcon("src\\ball.png");
        ball.setIcon(iconball);
        add(ball);

        Score = new JLabel(); 
        Score.setText("Score:" + String.valueOf(ScoreCounter)); //this set the text of jlabel 
        Score.setBounds(0, 0, 70, 20); //decide the position,width,height of this label
        add(Score);

        JLabel levelshow = new JLabel();
        levelshow.setText("Level: " + String.valueOf(level));//this set the text of jlabel 
        levelshow.setBounds(0, 20, 70, 20);//decide the position,width,height of this label
        add(levelshow);

        new Timer(1, e -> { // Makes a Timer which after every 1 millisecound is runned and makes the chnages to the jpannel
            if (labelYball == 0) { //when the timer will start y aixs of ball will be 0 so this will run 
                ((Timer) e.getSource()).stop(); //timer stoped
                JOptionPane.showMessageDialog(this,"Game instruction: \nYou have to catch the ball in the basket \nYou can move the basket using arrow keys"); //shows this message
                ((Timer) e.getSource()).start();//runs again

            }

            if (labelYball <= 1) { //this whenever y axis of ball is = to 1 or less then 1 will run to determine x axis of ball
                labelXball = rng.nextInt(800) - 1; //will randomely select x axis of ball between 1-800
            }

            labelYball = labelYball + speed; //will keep on adding speed int to y axis of ball to give a effect of moving ball
            if (intersects(ball, basket)) { //calls a method if true will run 
                ScoreCounter++; //add 1 to score int to count the score
                Score.setText("Score:" + String.valueOf(ScoreCounter)); //update the score in jpannel
                labelYball = 1;//resets the y axis to 1 to make the ball again from the start
                levelcounter++; // adds 1 to levelcounter keeing track of when to update level
                if (levelcounter == 3) { //when levelcounter = 3 will run. This if statment will excute when level will change 
                    level++; //will add 1 to level int
                    levelshow.setText("Level: " + String.valueOf(level));//updates level in jpannel
                    speed++;//addds 1 to speed to increase the speed at which th ball will be falling  
                    levelcounter = 0; // resets 
                    setBackground(new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256)));//this will chnage color of pannel randomly
                }

            }

            if (labelYball > 800) { //keeping track of if basket in unable to catch the ball will excute 
                ((Timer) e.getSource()).stop();//timer will stop
                JOptionPane.showMessageDialog(this, "      GAME OVER \n      You scored: " + ScoreCounter);//this will show to the player that the game is over and his scores
            }

            ball.setBounds(labelXball, labelYball, 50, 50);//this will keeping on changing position of ball giving it a illusion of falling 
        }).start();

    }

    public boolean intersects(JLabel ball, JLabel basket) { // got help from https://stackoverflow.com/questions/12325553/how-do-i-detect-the-collison-of-components
        Rectangle rectB = basket.getBounds();//makes a rectenagle by getting bounds of basket
        Rectangle result = SwingUtilities.computeIntersection( 
                ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight(), rectB);//this SwingUtilities.computeIntersection is used to check if there is intersection between ball and basket(which is rectb)
        return (result.getWidth() > 0 && result.getHeight() > 0);//then this checks if the result is greater then 0 indicating there is an intertection returning true othervise false
    }

    @Override
    public void keyTyped(KeyEvent e) { // implements KeyListener thats why even if i dont need some methods of keylistner class i still have to implement them
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

    @Override
    public void keyPressed(KeyEvent e) { //this when ever keyborad key is hit is called 
        int Keycode = e.getKeyCode();//get the code of which key is hit and store it in keycode
        if (labelXbasket > 800 || labelXbasket < 1) { //this check that the basket label should not go out of jframe window 
            if (labelXbasket > 800) { //if x of basket increses from more than 800 this will be called and will -15
                labelXbasket -= 15;
            }
            if (labelXbasket < 1) { //if x of basket decreses to 1 it will keep on adding 15
                labelXbasket += 15;
            }
            return;
        }
        if (Keycode == KeyEvent.VK_LEFT) { //the keycode if it equalls left arrow button  keycode  this will run
            labelXbasket -= 25;//and will decrese x axis of ball with 25 making it move 25 units left
            

        }
        if (Keycode == KeyEvent.VK_RIGHT) {//the keycode if it equalls right arrow button  keycode this will run
            labelXbasket += 25;//and will increse x axis of ball with 25 making it move 25 units right
            
        }
        basket.setBounds(labelXbasket, labelYbasket, 100, 100);// will chnage the position of the basket according to the code run before 
    }

    @Override
    public void keyReleased(KeyEvent e) {// implements KeyListener thats why even if i dont need some methods of keylistner class i still have to implement them
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}
