package me.dabpessoa.business.listeners;

import me.dabpessoa.bean.enums.DBModelAction;

public interface ActionListener {

	void doAction(Object obj, DBModelAction acao);

}
