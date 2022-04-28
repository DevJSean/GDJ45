package common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor   // public ActionForward(String view, boolean isRedirect) {
                      //    this.view = view;
					  //    this.isRedirect = isRedirect; }

public class ActionForward {
    // 어디로 어떻게 보낼 것인가
	private String View;        // 어디로   getView(),    setView()
	private boolean isRedirect; // 어떻게   isRedirect(), setRedirect()

}
