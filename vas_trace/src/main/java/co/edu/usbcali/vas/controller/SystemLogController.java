package co.edu.usbcali.vas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.usbcali.vas.model.dto.SystemLogDTO;
import co.edu.usbcali.vas.presentation.businessDelegate.IBusinessDelegatorView;



@Controller
@RequestMapping("/systemlog")
public class SystemLogController {
	private static final Logger log = LoggerFactory.getLogger(SystemLogController.class);
	
	@Autowired
	private IBusinessDelegatorView businessDelegator;

	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/save")
    public void save(@RequestBody SystemLogDTO systemLogDTO) throws Exception {
		log.info("VAS_TRACE SystemLogController save ");
		
        try {
            if(systemLogDTO != null) {
            	businessDelegator.save_systemLog(systemLogDTO);
            }
        	
        } catch (Exception e) {
        	e.printStackTrace();
        }

    }
	

}
