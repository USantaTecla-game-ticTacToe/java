package usantatecla.tictactoe.views.console;

import usantatecla.tictactoe.controllers.Logic;
import usantatecla.tictactoe.types.Error;
import usantatecla.tictactoe.views.PlayerView;

class UserPlayerView extends PlayerView {
    static final String ENTER_COORDINATE_TO_PUT = "Enter a coordinate to put a token:";
    static final String ENTER_COORDINATE_TO_REMOVE = "Enter a coordinate to remove a token:";

    UserPlayerView(Logic logic) {
        super(logic);
    }

    @Override
    public int[] readCoordinateToPut() {
        int[] coordinate = new int[2];
        Error error;
        do {
            coordinate = new CoordinateView(this.logic).read(ENTER_COORDINATE_TO_PUT);
            error = getErrorsPutCoordinate(coordinate[0], coordinate[1]);
            if (error != null) {
                new ErrorView(error).writeln();
            }
        } while (error != null);
        return coordinate;
    }

    @Override
    public int[][] readCoordinatesToMove() {
        int[] originCoordinate = new int[2];
        Error error;
        do {
            originCoordinate = new CoordinateView(this.logic).read(ENTER_COORDINATE_TO_REMOVE);
            error = getErrorsMoveOriginCoordinate(originCoordinate[0], originCoordinate[1]);
            if (error != null) {
                new ErrorView(error).writeln();
            }
        } while (error != null);
        int[] targetCoordinate = new int[2];
        do {
            targetCoordinate = new CoordinateView(this.logic).read(ENTER_COORDINATE_TO_PUT);
            error = getErrorsMoveTargetCoordinate(originCoordinate[0], originCoordinate[1], targetCoordinate[0],
                    targetCoordinate[1]);
            if (error != null) {
                new ErrorView(error).writeln();
            }
        } while (error != null);
        int[][] coordinates = new int[2][2];
        coordinates[0] = originCoordinate;
        coordinates[1] = targetCoordinate;
        return coordinates;
    }
}