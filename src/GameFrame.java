import javax.swing.JFrame;

public class GameFrame extends JFrame {//makes a JFrame with mention properties

    public GameFrame() {

        this.setTitle("GAME");//
        this.setSize(800, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel();//makes a obeject of class Jpannel

        add(panel);//add it to Jframae

        this.setVisible(true);

    }

}
