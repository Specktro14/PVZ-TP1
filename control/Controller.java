package pvz.control;

import static pvz.utils.StringUtils.isDigitString;

import pvz.logic.Game;
import pvz.utils.Position;
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

  // Atributes
  private final Game game;
  private final GameView view;

  // Constructors
  public Controller(Game game) {
    this.game = game;
    this.view = new GamePrinter(game);
  }

  /**
   * Runs the game logic.
   */
  public void run() {
    // Game loop
    while (!game.hasGameFinished()) {
      // Draw
      view.showGame();

      // User-Action
      boolean reseted = userAction();

      if (!reseted) {
        // Game_Actions
        game.addZombie();

        // Update
        game.update();
      }
    }
    view.showEndMessage();
  }

  // User-Action
  private boolean userAction() {
    boolean updated = false,
      reseted = false;

    while (!updated && !reseted) {
      // Get commands and args
      String[] words = view.getPrompt();
      String command = words[0].toLowerCase();

      // Reconocimiento de comando
      if (command.equals("add") || command.equals("a")) {
        // ADD
        if (addPlantCommand(words) == 0) {
          updated = true;
        }
      } else if (command.equals("reset") || command.equals("r")) {
        // RESET
        game.reset();
        reseted = true;
      } else if (command.equals("list") || command.equals("l")) {
        // LIST
        view.showMessage(Messages.LIST);
      } else if (command.equals("exit") || command.equals("e")) {
        // EXIT
        game.setPlayerQuits(true);
      } else if (command.equals("help") || command.equals("h")) {
        // HELP
        view.showMessage(Messages.HELP);
      } else if (
        command.equals("none") || command.equals("n") || command.equals("")
      ) {
        // NONE
        updated = true;
      } else {
        // UNKNOWN COMMAND
        view.showError(Messages.UNKNOWN_COMMAND);
      }
    }
    return reseted;
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
      if (words.length < 4) exitCode = 1;
      else exitCode = 5;
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
      case 5: {
        view.showError(Messages.TOO_MANY_COMMAND_PARAMETERS);
        break;
      }
      default:
        break;
    }

    return exitCode;
  }
}
