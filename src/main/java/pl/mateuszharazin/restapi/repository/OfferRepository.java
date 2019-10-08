package pl.mateuszharazin.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mateuszharazin.restapi.model.Offer;
import pl.mateuszharazin.restapi.model.Role;

public interface OfferRepository extends JpaRepository<Offer, Integer> {

    public Offer findAllById(int id);
//    public Offer findById(int id);

//    @Query("UPDATE job_offer SET title=?, max_salary=290.12, requirements='', description='', vacant_number= WHERE job_offer_id = 2";
//    )
//    public void updateOffer(Offer offer);


}
