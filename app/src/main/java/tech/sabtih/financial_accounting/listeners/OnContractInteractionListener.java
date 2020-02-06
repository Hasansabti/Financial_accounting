package tech.sabtih.financial_accounting.listeners;

import tech.sabtih.financial_accounting.models.Contract;
import tech.sabtih.financial_accounting.models.User;

public interface OnContractInteractionListener {

     void onContractClick(Contract contract);

    void onUserClick(User mItem);

    void onUserCreated(User user);
}
