//General
import java.util.*;
import java.lang.Math;
import java.io.*;
//Output Box
import java.awt.*;
import javax.swing.*;

class OutputBox
{
    
// - Class Fields - //

    //Frame
    JFrame frame = null;
    int frameHeight;
    //Image panel
    JLabel label = null;
    JPanel panel = null;
    ImageIcon imageIcon = null;
    Image image = null;
    int imageHeight = 250;
    int newWidth;
    //Text
    JTextArea textArea = null;
    JScrollPane scrollPane = null;
    int imageTextGap = 12;
    int textBoxLeft = 8;
    int textBoxWidth = 450;
    int textBoxHeight = 350;
    //Other
    int i;
    int gifChoice;


// - Alternate Constructor - //

    public OutputBox( String inProgramName, boolean inStartMessage )
    {
    
    // - Create Image Panel - //

        //Select gif
        int numGifs = new File( "C:\\Users\\James\\Google Drive\\#Coding\\Other\\Gifs" ).listFiles().length;
        gifChoice = randNum( 0, numGifs-1 );

        //Get image as Image Icon
        imageIcon = new ImageIcon( "C:\\Users\\James\\Google Drive\\#Coding\\Other\\Gifs\\" + gifChoice + ".gif" );
        //Get same image scaled
        newWidth = (int) ( (double) imageIcon.getIconWidth()/imageIcon.getIconHeight() * imageHeight );

        imageIcon = new ImageIcon( imageIcon.getImage().getScaledInstance( newWidth, imageHeight, 0 ) );

        //Create label and set icon as image
        label = new JLabel();
        label.setIcon( imageIcon );

        //Create image panel and add image label
        panel = new JPanel();
        panel.add( label );
        
    // - Create Frame - //
        
        frame = new JFrame( inProgramName );
        frameHeight = 20 + imageHeight + imageTextGap + textBoxHeight + 20 + 2;

        //If image width > text box width
        if ( imageIcon.getIconWidth() > textBoxWidth )
        {
            //Set frame width slightly wider than image
            frame.setSize( imageIcon.getIconWidth()+30, frameHeight );
            //Set text box left position to half of space left
            textBoxLeft = (frame.getWidth() / 2) - (textBoxWidth/2) - 4;
        }
        //Else
        else
        {
            //Set frame width slightly wider than text box
            frame.setSize( textBoxWidth+30, frameHeight );
            //Set text box left position 10
            textBoxLeft = 9;
        }
        
        frame.setLocationRelativeTo(null);
        frame.setLocation( (int)frame.getLocation().getX(), (int)frame.getLocation().getY() - 100 );
        frame.setDefaultCloseOperation( 3 );
        frame.setResizable( false );
        
    // - Create Text Area Inside Scroll Pane - //

        //Create text area
        textArea = new JTextArea( "" );
        textArea.setFont ( new Font("Dialog", 0, 12) );
        textArea.setBackground( new Color(200, 100, 50) );
        textArea.setEditable(false);
        
        //Create scrollpane with text area inside
        scrollPane = new JScrollPane( textArea );
         //Add border to text area and scrollpane
        textArea.setBorder( BorderFactory.createMatteBorder(8, 15, 5, 5, new Color(200, 100, 50)) );
        //Set position of scrollpane
        scrollPane.setBounds( textBoxLeft, imageHeight+12, textBoxWidth, textBoxHeight ); //(Random +X as doesn't sit perfectly, needed to adjust)

    // - Add Scroll Pane to Main Panel - //

        frame.add( scrollPane );
        
    // - Add Main Panel to Frame - //

        frame.add( panel );

    // - Show Frame - //

        frame.setVisible( true );

    // - Start Message - //
    
        //If want start message
        if ( inStartMessage )
        {
            //Print start messagee
            update( "Starting program..." );
        }

    }




// - update() Method - //

    //Add text to current text area
    public void update( String inText )
    {
        //Add inText to textFrame then new line at top of text area
        textArea.append( inText + "\n" );
        //Pause
        sleep( 0.08 );
        // Scroll to bottom
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }


// - newHeading() Method - //

    //Adds "= = ... " to text area
    public void newHeading()
    {
        update( "= = = = = = =" );
        //Pause
        sleep( 0.3 );
    }

    
// - allDone() Method - //

    //Print 'All done' message for end of programs
    public void allDone()
    {
        newHeading();
        //Add 'All done' mesage to textFrame (Need to add manually as 'update()' includes pause)
        textArea.insert( "All done mate!\n", 0 );
        textArea.setFont( new Font("TimeRoman", 1, 16) );
        sleep( 0.4 );
        textArea.setFont( new Font("TimeRoman", 0, 16) );
        sleep( 0.4 );
    }


// - Random Number - //

    public static int randNum( int inMin, int inMax )
    {
        int outNum = (int) ( ( Math.random() * ((inMax - inMin) + 1)) + inMin );
        return outNum;
    }

    
// - sleep() Method - //

    //Sleep for given amount of seconds in X.YY
    public void sleep( double inTime )
    {
        try
        {
            Thread.sleep( (int)(inTime*1000) );
        }
        catch ( InterruptedException e )
        {
            //Use update method to print error
            update( "Error with sleep method!" );
        }
    }
    
} 
