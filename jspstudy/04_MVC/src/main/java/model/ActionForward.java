package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ActionForward {

	private boolean isRedirect; // redirect면 true, forward면 false (디폴트는 forward)
	private String view;        // 
	
	
}
