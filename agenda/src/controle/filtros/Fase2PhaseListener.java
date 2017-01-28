package controle.filtros;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

@SuppressWarnings("serial")
public class Fase2PhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent arg0) {
		System.out.println("*** após a Fase - " + this.getPhaseId());
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		System.out.println("*** antes da Fase - " + this.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.APPLY_REQUEST_VALUES;
	}

}
