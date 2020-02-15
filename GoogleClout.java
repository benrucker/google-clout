package Launchpad;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;


public class GoogleClout
{
  JFrame window;
  Container content;
  JButton reset;
  JButton close;
  JButton addURL;
  JPanel topPanel;
  JPanel midPanel;
  JPanel botPanel;
  JLabel directions;
  JTextField URLentry;
  public ArrayList<String> enteredURLs;



  public GoogleClout() throws Exception{

    File history = new File("URLhistory.txt");
    if(history.createNewFile()){
      System.out.println("User history file created in directory.");
    }

    enteredURLs = loadPrevURLs("URLhistory.txt");

    //set layout
    window = new JFrame("URL-Pad");
    content = window.getContentPane();
    content.setLayout(new GridLayout(3,1));
    ButtonListener listener = new ButtonListener();


    addURL= new JButton("Add URL");
    addURL.setFont(new Font("verdana", Font.BOLD, 20 ));
    addURL.addActionListener( listener );

    close = new JButton("Save & Close");
    close.setFont(new Font("verdana", Font.BOLD, 20 ));
    close.addActionListener( listener );

    //top panel
    topPanel = new JPanel();
    directions = new JLabel("Enter your URL below: ");
    directions.setFont(new Font("verdana", Font.BOLD, 24 ));
    topPanel.add(directions);

    //mid panel
    URLentry = new JTextField(20);
    URLentry.setToolTipText("Enter URL here: ");
    midPanel = new JPanel();
    midPanel.add(URLentry);



    //bottom panel
    botPanel = new JPanel();
    botPanel.setPreferredSize(new Dimension(300, 150));
    botPanel.setLayout(new GridLayout(1,3));
    //botPanel.add(reset);
    botPanel.add(addURL);
    botPanel.add(close);

    window.add(topPanel);
    window.add(midPanel);
    window.add(botPanel);

    
    window.setSize( 375,200);
    window.setLocationRelativeTo(null);
    window.setVisible(true);

  }

  public ArrayList<String> getArrayList(int input){
      return enteredURLs;
  }

  class ButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {

      Component whichButton = (Component) e.getSource();

      //get URLs (Call Reddit Function)
      if(whichButton == reset)
        System.out.println("New URLs loaded");

      //add URL
      if(whichButton == addURL){
        enteredURLs.add(URLentry.getText());

        PrintWriter URLhistory = null;
        try {
          URLhistory = new PrintWriter(new FileWriter("URLhistory.txt",true));
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        URLhistory.append(URLentry.getText()).append("\n");
        URLhistory.close();

        URLentry.setText("");
      }

      //close app
      if(whichButton == close)
        System.exit(0);
    }
  }

  public ArrayList<String> loadPrevURLs (String filename) throws FileNotFoundException {

    ArrayList<String> URLarray = new ArrayList<>();
    Scanner infile = new Scanner( new File(filename));
    while ( infile.hasNext() )
    {
      String word = infile.nextLine();
      if (word != null)
        URLarray.add(word.trim());
    }
    infile.close();
    return URLarray;
  }
}


