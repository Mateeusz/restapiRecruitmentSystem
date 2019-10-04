package pl.mateuszharazin.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mateuszharazin.restapi.model.Offer;
import pl.mateuszharazin.restapi.repository.OfferRepository;

import java.util.List;

@Service
public class OfferServiceImp implements OfferService{


    @Autowired
    OfferRepository offerRepository;


    @Override
    public void saveOffer(Offer offer) {
        //tutaj można by dopisać do oferty człowieka który ją dodał
        offerRepository.save(offer);
    }

    @Override
    public List<Offer> listOffer() {

        return offerRepository.findAll();
    }
}
