package pl.mateuszharazin.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateuszharazin.restapi.model.Offer;
import pl.mateuszharazin.restapi.model.Role;

public interface OfferRepository extends JpaRepository<Offer, Integer> {

    public Offer findAllById(int id);

}
