package me.dabpessoa.business.listeners;

import me.dabpessoa.bean.enums.DBModelAction;

public interface DBModelActionListener {

	void doAction(Object obj, DBModelAction acao);

}
