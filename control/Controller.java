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
		
		while(!game.hasGameFinished() && !game.playerQuits()) {
		  // Draw
			if (!message) {
			  view.showGame();
			}

			// User Action
			String[] words = view.getPrompt();

			// Añadir short commmand y refactorizar
			switch(words[0]) {
			  case "add": {
					if (addPlantCommand(words) == 0) {
					  message = false;
					}
					else {
					message = true;					
					}					
					
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
				  view.showMessage(Messages.GAME_OVER);
				  view.showMessage(Messages.PLAYER_QUITS);
					game.setEndGame(true);
				  break;
				}
				case "help": {
				  view.showMessage(Messages.HELP);
					message = true;
					break;
				}
				case "none": {
				} 
				case Messages.EMPTY_STRING: {
				  break;
				}
				default: {
				  view.showMessage(Messages.UNKNOWN_COMMAND);
					message = true;
				  break;
				}
			}

			if (!message || !game.hasGameFinished()) {
			  game.update();
			}
		}
	}

 private int addPlantCommand(String[] words) {
    int exitCode = 0;

    // Comprobar que esten todos los argumentos
    if (words.length == 4) {
      // Comprobar que los dos ultimos argumentos sean numeros
      if (isDigitString(words[2]) && isDigitString(words[3])) {
        Position pos = new Position(words[3], words[2]);
        // Comprobar que la posicion esta vacia y esta dentro del tablero
        if (game.correctPosition(pos)) {
          // Comprobar que es una planta
          if (game.checkGameObject(words[1])) {
            // Añadimos planta, game se encarga de mirar las suncoins
            exitCode = game.addGameObject(words[1], pos);
          } else {
            exitCode = 2;
          }
        } else {
          exitCode = 3;
        }
      } else {
        exitCode = 3;
      }
    } else {
      exitCode = 1;
    }

    // Mostrando posibles errores
    switch (exitCode) {
      case 1: {
        view.showError(Messages.COMMAND_PARAMETERS_MISSING);
        break;
      }
      case 2: {
        view.showError(Messages.INVALID_GAME_OBJECT);
        break;
      }
      case 3: {
        view.showError(Messages.INVALID_POSITION);
        break;
      }
      case 4: {
        view.showError(Messages.NOT_ENOUGH_COINS);
        break;
      }
      default:
        break;
    }

    return exitCode;
  }
}