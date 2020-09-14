package edu.jsu.mcis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import javax.swing.JButton;

public class TicTacToeController implements ActionListener {

    private final TicTacToeModel model;
    private final TicTacToeView view;
    
    /* CONSTRUCTOR */

    public TicTacToeController(final int width) {

        /* Initialize model and view */

        model = new TicTacToeModel(width);
        view = new TicTacToeView(this, width);

    }

    public String getMarkAsString (int row, int col)
    {
        return(model.getMark(row, col).toString());
    }

    public TicTacToeView getView()
    {
        return view;
    }

    @Override
    public void actionPerformed (ActionEvent event)
    {
        /* This is the event handler for button clicks. 
        First, acquire a reference to the clicked button and get its name */

        String name = ((JButton) event.getSource()).getName();

        // Parse the row and column from the name

        int row = parseInt(name.substring(6, 7));
        int col = parseInt(name.substring(7, 8));

        //Make a mark at the specified row and column

        model.makeMark(row, col);

        //Update the view to show the new mark

        view.updateSquares();

        //Get new result

        TicTacToeModel.Result result = model.getResult();

        //if the game is over, disable the square and show the result

        if(result != TicTacToeModel.Result.NONE)
        {
            view.disableSquares();
            view.showResult(result.toString());
        }
        //Otherwise, leave the result field empty

        else 
        {
            view.clearResult();

        }
    }
}
