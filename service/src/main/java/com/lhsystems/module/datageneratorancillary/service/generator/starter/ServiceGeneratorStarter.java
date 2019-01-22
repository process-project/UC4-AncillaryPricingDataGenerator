package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.Donation;
import com.lhsystems.module.datageneratorancillary.service.data.EntertainmentOnBoard;
import com.lhsystems.module.datageneratorancillary.service.data.Flowers;
import com.lhsystems.module.datageneratorancillary.service.data.Hotel;
import com.lhsystems.module.datageneratorancillary.service.data.Parking;
import com.lhsystems.module.datageneratorancillary.service.data.Service;
import com.lhsystems.module.datageneratorancillary.service.data.WiFi;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.ServiceConfiguration;
import com.lhsystems.module.datageneratorancillary.service.repository.DonationRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.EntertainmentOnBoardRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.FlowersRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.HotelRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.ParkingRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.WiFiRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Starts generating service Entities and saves them into repositories.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@org.springframework.stereotype.Service
public final class ServiceGeneratorStarter {


    /** The donation repository. */
    private final DonationRepository donationRepository;

    /** The entertainment on board repository. */
    private final EntertainmentOnBoardRepository entertainmentOnBoardRepository;

    /** The flowers repository. */
    private final FlowersRepository flowersRepository;

    /** The hotel repository. */
    private final HotelRepository hotelRepository;

    /** The parking repository. */
    private final ParkingRepository parkingRepository;

    /** The wifi repository. */
    private final WiFiRepository wiFiRepository;

    /**
     * Instantiates a new service generator starter.
     *
     * @param paramDonationRepository
     *            the donation repository
     * @param paramEntertainmentOnBoardRepository
     *            the entertainment on board repository
     * @param paramFlowersRepository
     *            the flowers repository
     * @param paramHotelRepository
     *            the hotel repository
     * @param paramParkingRepository
     *            the parking repository
     * @param paramWiFiRepository
     *            the wifi repository
     */
    @Autowired
    public ServiceGeneratorStarter(
            final DonationRepository paramDonationRepository,
            final EntertainmentOnBoardRepository paramEntertainmentOnBoardRepository,
            final FlowersRepository paramFlowersRepository,
            final HotelRepository paramHotelRepository,
            final ParkingRepository paramParkingRepository,
            final WiFiRepository paramWiFiRepository) {
        donationRepository = paramDonationRepository;
        entertainmentOnBoardRepository = paramEntertainmentOnBoardRepository;
        flowersRepository = paramFlowersRepository;
        hotelRepository = paramHotelRepository;
        parkingRepository = paramParkingRepository;
        wiFiRepository = paramWiFiRepository;
    }

    /**
     * Generate service entities based on configuration information.
     *
     * @param paramServiceConfiguration
     *            the service configuration
     * @return the list
     */
    public List<Service> generateServiceEntities(
            final ServiceConfiguration paramServiceConfiguration) {
        final ArrayList<Service> services = new ArrayList<>();

        final Donation donation = new Donation(
                paramServiceConfiguration.getDonationMean(),
                paramServiceConfiguration.getDonationStandardDeviation(),
                paramServiceConfiguration.getDonationName());
        services.add(donation);
        donationRepository.save(donation);
        final EntertainmentOnBoard entertainmentOnBoard = new EntertainmentOnBoard(
                paramServiceConfiguration.getEntertainmentOnBoardPriceMean());
        services.add(entertainmentOnBoard);
        entertainmentOnBoardRepository.save(entertainmentOnBoard);
        final Flowers flowers =  new Flowers(
                paramServiceConfiguration.getFlowersName(),
                paramServiceConfiguration.getFlowersCapacity(),
                paramServiceConfiguration.getFlowersFixedPrice());
        services.add(
                flowers);
        flowersRepository.save(flowers);
        final Hotel hotel = new Hotel(
                paramServiceConfiguration.getHotelName(),
                paramServiceConfiguration.getHotelMeanPrice());
        services.add(
                hotel);
        hotelRepository.save(hotel);
        final Parking parking = new Parking(
                paramServiceConfiguration.getParkingCapacity(),
                paramServiceConfiguration.getParkingFixedPrice());
        services.add(parking);
        parkingRepository.save(parking);
        final WiFi wifi = new WiFi(
                paramServiceConfiguration.getWiFiPriceIntercontinental(),
                paramServiceConfiguration.getWiFiPriceShortRange());
        services.add(
                wifi );
        wiFiRepository.save(wifi);
        return services;
    }
}
