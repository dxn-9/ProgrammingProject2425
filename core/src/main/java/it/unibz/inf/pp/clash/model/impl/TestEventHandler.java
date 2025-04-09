package it.unibz.inf.pp.clash.model.impl;

import it.unibz.inf.pp.clash.model.EventHandler;
import it.unibz.inf.pp.clash.model.snapshot.Snapshot;
import it.unibz.inf.pp.clash.model.snapshot.impl.dummy.DummySnapshot;
import it.unibz.inf.pp.clash.model.snapshot.units.Unit;
import it.unibz.inf.pp.clash.view.DisplayManager;

public class TestEventHandler implements EventHandler {
    DisplayManager displayManager;
    DummyEventHandler dummyEventHandler;
    Snapshot snapshot;

    public TestEventHandler(DisplayManager displayManager) {
        this.dummyEventHandler = new DummyEventHandler(displayManager);
        this.displayManager = displayManager;
    }

    @Override
    public void newGame(String firstHero, String secondHero) {
        snapshot = new DummySnapshot(
                firstHero,
                secondHero
        );

        displayManager.drawSnapshot(snapshot, "New game");
    }

    @Override
    public void exitGame() {
        dummyEventHandler.exitGame();
    }

    @Override
    public void skipTurn() {

        dummyEventHandler.skipTurn();
    }

    @Override
    public void callReinforcement() {
        dummyEventHandler.callReinforcement();

    }

    @Override
    public void requestInformation(int rowIndex, int columnIndex) {
        dummyEventHandler.requestInformation(rowIndex, columnIndex);

    }

    Unit _unit = null;
    int previousRowIndex, previousColumnIndex;

    @Override
    public void selectTile(int rowIndex, int columnIndex) {
        System.out.println("Hello");
        var unit = snapshot.getBoard().getUnit(rowIndex, columnIndex);
        if (unit.isPresent()) {
            System.out.println("Unit found");
            _unit = unit.get();
            previousRowIndex = rowIndex;
            previousColumnIndex = columnIndex;
            return;
        }

        if (_unit != null) {
            System.out.println("Placing here" + rowIndex + "  " + columnIndex);
            snapshot.getBoard().addUnit(rowIndex, columnIndex, _unit);
            snapshot.getBoard().removeUnit(previousRowIndex, previousColumnIndex);
            displayManager.drawSnapshot(snapshot, "Lule");
        }


    }

    @Override
    public void deleteUnit(int rowIndex, int columnIndex) {
        dummyEventHandler.deleteUnit(rowIndex, columnIndex);

    }
}
