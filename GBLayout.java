
package MiniProjects;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.geom.Line2D;
import java.io.*;




public class GBLayout implements ActionListener {
   
    class MyDrawingPanel extends JPanel{
    //int xyz;    
    public void paintComponent(Graphics g){
            
            g.setColor(Color.blue);
        //    g.drawLine(getWidth()/2, getHeight()/2,getWidth() , getHeight()/2);
            g.fillOval(150,150,xyz,xyz);
        }
    }
int score = 0;
    
    int[][] counter = new int[10][10];
    JFrame jp;
    JButton[][] buttons = new JButton[10][10];
    int xyz = 0;
    int x,y = 0;
    int zeros = 0;
    
    JButton lnew = new JButton("Game Over. PRESS BUTTON TO RESTART");
    JFrame game = new JFrame("You lose");
    final int MINE = 100;
   JButton reset = new JButton("Reset");
    GridBagLayout gd = new GridBagLayout();
    JPanel backgroundPanel;
    JPanel panel = new JPanel();
    
    int neighbours = 0;
    
    JMenuBar menu = new JMenuBar();
    
     MyDrawingPanel panel2 = new MyDrawingPanel();
     
     int fileTransfer(int highScore){   
        try{
            FileOutputStream fout = new FileOutputStream("data.txt");
            FileInputStream fin = new FileInputStream("data.txt");
            fout.write(score);
            System.out.println(fin.read());
            System.out.println(score);
            
        }
        catch(Exception ex){
            
        } 
        return score;
}
    
    public static void main(String[] args){
         GBLayout gbl = new GBLayout();
         gbl.go();
        // gbl.fileTransfer();
        
     
    }
    
 /*   protected void paintComponent(Graphics g) {
        
       // super.paintComponent(g);
        g.setColor(Color.black);
        g.drawLine(0, 1, 2, 3);
        g.setColor(Color.black);
            g.fillOval(x, y, 40, 40);
    
}*/
   
    
    public void go(){
        
        
        
        ArrayList<Integer> array = new ArrayList<>();
       lnew.addActionListener(this);
     /*lnew.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e)
    {
       game.dispose();
    }
});  */
     /* Loop for containing data about the individual cells in the grids 
        for(int a = 0; a < 10; a++){
            for(int b = 0; b < 10; b++){
                
            }
        }*/
        Container grid = new Container();
        
        grid.setLayout(new GridLayout(10,10));
        panel.setLayout(new BorderLayout());
        for(int a = 0; a < 10;a++){
            for(int b = 0;b < 10;b++){
                buttons[a][b] = new JButton();
              //  buttons[a][b].setSize(10,10);
                buttons[a][b].addActionListener(this);
                grid.add(buttons[a][b]);
            }
        }
        panel.add(grid,BorderLayout.CENTER);
        
        for(int i = 0; i < 20; i++){
            int ran = (int)(Math.random()*100);
            System.out.println(""+ran);
            
            counter[ran/10][ran%10] = MINE;
            
        }
         System.out.println("___________");
       for(int a = 0; a < 10; a++){
            for(int b = 0; b < 10; b++){
                neighbours = 0;
                if(counter[a][b] == MINE){
                    continue;
                }
                else{
                    counter[a][b] = checkNeighbours(a, b);
                }
            }
        }
    
       
       reset.addActionListener(this); 
        
        backgroundPanel = new JPanel();
      
        
      // panel2.add(button);
      //  panel2.add(reset);
       
        jp = new JFrame();
        jp.setSize(1000,600);
        JTextArea textArea = new JTextArea(1,5);
        panel2.add(textArea);
        textArea.setEditable(false);
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.X_AXIS));
        
        backgroundPanel.setOpaque(true);
        
        
        panel.setBackground(Color.BLUE);
        
           
        panel.setPreferredSize(new Dimension(400,200));
        panel2.setPreferredSize(new Dimension(400,200));
        
        jp.setVisible(true);
        
        
        panel2.setBackground(Color.BLACK);
        
        backgroundPanel.add(panel2);
        backgroundPanel.add(panel);
        
        panel2.setOpaque(true);
        panel2.add(reset);
        panel.setOpaque(true);
        
        
        
         jp.add(backgroundPanel);
         jp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
     //   panel.setLayout(gd);
     
          panel2.repaint();
        
           
       
      //jp.setContentPane(panel);
      //   backgroundPanel.add(Box.createRigidArea(new Dimension(5,0)));
    }
    public int checkNeighbours(int one,int two){
        
        if((one -1) != -1 && (two-1) != -1){
            if(counter[one-1][two-1] == MINE)
            neighbours++;
        }
        if((one-1) != -1){
           if(counter[one-1][two] == MINE)
            neighbours++;
        }
        if((one -1) != -1 && (two+1) != 10){
            if(counter[one-1][two+1] == MINE)
            neighbours++;
        }
        if((two-1) != -1){
        if(counter[one][two-1] == MINE)    
            neighbours++;
        }
        if((two-1) != -1 && (one+1) != 10){
            if(counter[one+1][two-1] == MINE)
            neighbours++;
        }
        if((two+1) != 10){
        if(counter[one][two+1] == MINE)    
            neighbours++;
        }
        if((one+1) != 10 && (two+1) != 10){
            if(counter[one+1][two+1] == MINE)
              neighbours++;
        }
       if((one+1) != 10){
            if(counter[one+1][two] == MINE)
               neighbours++;
        }
        
        return neighbours;
        
    }
    public void checkZeroes(int one,int two){
        buttons[one][two].setText("0");
        
        if((one -1) != -1 && (two-1) != -1){
            if(counter[one-1][two-1] == 0 && buttons[one-1][two-1].isEnabled()){
                buttons[one-1][two-1].setEnabled(false);
                buttons[one-1][two-1].setText("0");
                checkZeroes(one-1,two-1);
            }
            
        }
        if((one-1) != -1){
           if(counter[one-1][two] == 0 && buttons[one-1][two].isEnabled()){
               buttons[one-1][two].setEnabled(false);
               buttons[one-1][two].setText("0");
               checkZeroes(one-1,two);
           }
            
        }
        if((one -1) != -1 && (two+1) != 10){
            if(counter[one-1][two+1] == 0 && buttons[one-1][two+1].isEnabled()){
                buttons[one-1][two+1].setEnabled(false);
                buttons[one-1][two+1].setText("0");
                checkZeroes(one-1,two+1);
            }
            
        }
        if((two-1) != -1){
        if(counter[one][two-1] == 0 && buttons[one][two-1].isEnabled()){
            buttons[one][two-1].setEnabled(false);
            buttons[one][two-1].setText("0");
            checkZeroes(one,two-1);
        }    
            
        }
        if((two-1) != -1 && (one+1) != 10){
            if(counter[one+1][two-1] == 0 && buttons[one+1][two-1].isEnabled()){
                buttons[one+1][two-1].setEnabled(false);
                buttons[one+1][two-1].setText("0");
                checkZeroes(one+1,two-1);
            }
            
        }
        if((two+1) != 10){
        if(counter[one][two+1] == 0 && buttons[one][two+1].isEnabled()){
            buttons[one][two+1].setEnabled(false);
            buttons[one][two+1].setText("0");
            checkZeroes(one,two+1);
        }    
            
        }
        if((one+1) != 10 && (two+1) != 10){
            if(counter[one+1][two+1] == 0 && buttons[one+1][two+1].isEnabled()){
                buttons[one+1][two+1].setEnabled(false);
                buttons[one+1][two+1].setText("0");
                checkZeroes(one+1,two+1);
            }
              
        }
       if((one+1) != 10){
            if(counter[one+1][two] == 0 && buttons[one+1][two].isEnabled()){
                buttons[one+1][two].setEnabled(false);
                buttons[one+1][two].setText("0");
                checkZeroes(one+1,two);
            }
               
        }
        
       
        
    }
    
    public void actionPerformed(ActionEvent event){
        if(event.getSource().equals(reset)){
           
            game.setVisible(false);
           jp.setVisible(false);
            
            new GBLayout().go();
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    buttons[i][j].setText("");
                    buttons[i][j].setEnabled(true);
                }
            }
            
        }
        if(event.getSource().equals(lnew)){
           game.setVisible(false);
           jp.setVisible(false);
            
            new GBLayout().go();
        }
        else{
        for(int a = 0; a < 10; a++){
            for(int b = 0; b < 10; b++){
                if(event.getSource().equals(buttons[a][b])){
                    
                    if(counter[a][b] == MINE){
                        
                                  game.setSize(400,400);
                             //     JButton lnew = new JButton("Game Over. PRESS TO RESTART");
                                  game.add(lnew);
                                  game.setVisible(true);
                      for(int i = 0;i < 10; i++){
                          for(int j = 0;j < 10; j++){
                              
                              if(counter[i][j] == MINE){
                                  
                                  buttons[i][j].setEnabled(false);
                                  buttons[i][j].setBackground(Color.red);
                                  buttons[i][j].setText("X");
                                  
                              }
                              buttons[i][j].setEnabled(false);
                          }
                      }
                    }
                    else if(counter[a][b] != MINE && counter[a][b]!=0){
                        buttons[a][b].setText(counter[a][b]+"");
                        score++;
                        fileTransfer(score);
                        
                    }
                    else if(counter[a][b] == 0){
                        buttons[a][b].setEnabled(false);
                        checkZeroes(a,b);
                        
                        
                    }
                }
                    
                    
            }
        }
        }
    }
    
    
    
      void makeButton(JButton button,GridBagLayout gd, int gridwidth,int gridheight, double weightx, double weighty, int gridx, int gridy){
        GridBagConstraints c = new GridBagConstraints();
        
        button.setFont(new Font("monospaced", Font.BOLD, 21));
        button.setForeground(Color.DARK_GRAY);
        button.setBackground(Color.LIGHT_GRAY);
        
        if("Aditya".equals(button.getName())){
            c.anchor = GridBagConstraints.FIRST_LINE_START;
        }
        c.gridwidth = gridwidth;
        c.weightx = weightx;
        c.weighty = weighty;
        c.gridx = gridx;
        c.gridy = gridy;
        c.ipady = 20;
        c.ipadx = 20;
     //   c.insets = new Insets(50,2,2,2);
        c.fill = GridBagConstraints.BOTH;
        gd.setConstraints(button, c);
        panel.add(button);
    }

    
}
