package pl.mateuszharazin.restapi.service;

import org.springframework.stereotype.Service;
import pl.mateuszharazin.restapi.model.Offer;

import java.util.List;

public interface OfferService {

    public void deleteOffer(int id);
//    public void editOffer(Offer offer);
    public void newOffer(Offer offer);
    public List<Offer> listOffer();

}
