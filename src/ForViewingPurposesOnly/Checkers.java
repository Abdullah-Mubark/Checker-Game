package ForViewingPurposesOnly;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Checkers extends JFrame
{
   public Checkers(String title)
   {
      super(title);
      setDefaultCloseOperation(EXIT_ON_CLOSE);

      Board board = new Board();
      for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 2; j++) {
                board.add(new Checker(CheckerType.RED_REGULAR), j, i);
                board.add(new Checker(CheckerType.BLACK_REGULAR), 9-j, i);
            }
        }
      setContentPane(board);

      pack();
      setVisible(true);
   }

   public static void main(String[] args)
   {
      Runnable r = new Runnable()
                   {
                      @Override
                      public void run()
                      {
                         new Checkers("Checkers");
                      }
                   };
      EventQueue.invokeLater(r);
   }
}