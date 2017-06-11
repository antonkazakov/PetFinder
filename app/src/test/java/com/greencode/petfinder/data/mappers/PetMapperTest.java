package com.greencode.petfinder.data.mappers;

import com.greencode.petfinder.data.entity.beans.pet.PetGetResponse;
import com.greencode.petfinder.data.entity.locanbeans.pet.Contact;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.entity.locanbeans.pet.Photo;
import com.greencode.petfinder.utils.SimpleXMLParser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.simpleframework.xml.core.Persister;

import io.realm.RealmList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Anton Kazakov
 * @date 08.04.17.
 */
public class PetMapperTest {

    private PetMapper petMapper;

    private SimpleXMLParser simpleXMLParser;

    private static final String GET_PET_RESPONSE = "/Users/antonkazakov/android/PetFinder/app/src/test/resources/xml/get_pet.xml";

    @Before
    public void setUp() throws Exception {
        petMapper = new PetMapper();
        simpleXMLParser = new SimpleXMLParser(new Persister());
    }

    @Test
    public void testTransformSingle() throws Exception {
        PetGetResponse petGetResponse = simpleXMLParser.parse(GET_PET_RESPONSE, PetGetResponse.class);

        Pet actualPet = petMapper.transform(petGetResponse);
        Pet expectedPet = new Pet();
        expectedPet.setId("37432659");
        expectedPet.setName("Teddy");
        expectedPet.setSex("M");
        expectedPet.setAnimal("Cat");
        expectedPet.setAge("Senior");
        expectedPet.setSize("M");

        RealmList<Photo> photoList = new RealmList<>();
        photoList.add(new Photo("37432659_1",
                "http://photos.petfinder.com/photos/pets/37432659/1/?bust=1487198829&width=500&-x.jpg"));
        photoList.add(new Photo("37432659_2",
                "http://photos.petfinder.com/photos/pets/37432659/2/?bust=1487198829&width=500&-x.jpg"));
        expectedPet.setPhotos(photoList);
        Contact contact = new Contact();
        contact.setState("QC");
        contact.setPhone("514-726-3571");
        contact.setCity("Montreal");
        contact.setEmail("assml@videotron.ca");
        contact.setZipcode("H2L 3L7");
        expectedPet.setContact(contact);

        assertNotNull(actualPet);
        assertEquals(expectedPet, actualPet);
    }


    @After
    public void tearDown() throws Exception {

    }


}