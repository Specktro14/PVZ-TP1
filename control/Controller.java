package pvz.control;

import pvz.logic.Game;
import pvz.view.GamePrinter;
import pvz.view.GameView;
import pvz.view.Messages;

/**
 * Input/output coordinator of the game (the C in MVC).
 *
 * <p>Owns the game loop: reads a line from stdin, parses it into a
 * command, validates parameters (plant type, position), delegates
 * state changes to {@link Game}, and triggers a board reprint via
 * {@link tp1.pvz.view.GameView>} when the cycle advances. It holds no game
 * state of its own; the source of truth is always {@link Game}.
 */
public class Controller {

	private final Game game;
	private final GameView view;

	public Controller(Game game) {
		this.game = game;
		this.view = new GamePrinter(game);
	}

	/**
	 * Runs the game logic.
	 */
	public void run() {
		// TODO fill your code
		// 
		// Posibilidad ALTA de cambios
		boolean message = false;
		
		while(!game.hasEnded()) {
		  // Draw
			if (!message) {
			  view.showGame();
			}

			// User Action
			String[] words = view.getPrompt();

			switch(words[0]) {
			  case "add": {
					
			    break;
			  }
				case "reset": {
				  break;
				}
				case "list": {
				  view.showMessage(Messages.LIST);
					message = true;
				  break;
				}
				case "exit": {
				  break;
				}
				case "help": {
				  view.showMessage(Messages.HELP);
					message = true;
					break;
				}
				case "none": {
				} 
				default: {
				  break;
				}
			}

			if (!message) {
			
			}
		}
	}
}