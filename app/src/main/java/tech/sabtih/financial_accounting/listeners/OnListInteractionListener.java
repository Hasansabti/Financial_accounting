package tech.sabtih.financial_accounting.listeners;

public interface OnListInteractionListener {

    public void onSelectModeStarted();
    void onSelectModeEnded();
    void onSelectionUpdated(int selected);
}
