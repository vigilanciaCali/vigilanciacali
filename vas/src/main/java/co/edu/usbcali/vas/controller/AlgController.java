package co.edu.usbcali.vas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.usbcali.vas.presentation.businessDelegate.IBusinessDelegatorView;

@Controller
@RequestMapping("/alg")
public class AlgController {
	private static final Logger log = LoggerFactory.getLogger(AlgController.class);

	@Autowired
	private IBusinessDelegatorView businessDelegator;

	//Data is already on vasanl queue
	@PostMapping(value = "/algResult/")
	public ResponseEntity<String> requestAlgResult() throws Exception {
			log.info("1. VAS AlgController algResult");

			try {

				businessDelegator.startAlgResult();

				// log.info("VAS_SERVER_MONITOR requestAnlAlg status"+status);
				return ResponseEntity.ok().body("algResult received");
			} catch (Exception e) {
				log.error("VAS AlgController algResult", e.getMessage(), e);
				return ResponseEntity.badRequest().body(e.getMessage());
			}

		}

}
