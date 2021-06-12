package kodlamaio.hrms.core.adapters;

import java.rmi.RemoteException;
import java.util.Locale;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.userValidation.UserCheckService;

import kodlamaio.hrms.entities.concretes.Candidate;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisServiceAdapter implements UserCheckService {

	@Override
	public boolean checkIfRealPerson(Candidate candidate) {
		
		KPSPublicSoapProxy client = new KPSPublicSoapProxy();
		boolean result = false;
		 try {
			result = client.TCKimlikNoDogrula(Long.parseLong(candidate.getIdentityNumber()),					
					candidate.getFirstName().toUpperCase(new Locale("tr")),
					candidate.getLastName().toUpperCase(new Locale("tr")),
					candidate.getBirthYear());
		/* }catch(Exception e) {
			 System.out.println("Not a valid person");
		 }*/
			}catch (NumberFormatException e) {
				
				e.printStackTrace();
				
			} catch (RemoteException e) {
				
				e.printStackTrace();
				
			}
			
		return result;
		 
	}

}
