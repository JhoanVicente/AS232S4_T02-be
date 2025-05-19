package pe.edu.vallegrande.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.backend.dto.ReservationRequestDTO;
import pe.edu.vallegrande.backend.model.OrderStatusType;
import pe.edu.vallegrande.backend.model.Reservation;
import pe.edu.vallegrande.backend.model.ReservationDetail;
import pe.edu.vallegrande.backend.model.RestaurantTable;
import pe.edu.vallegrande.backend.model.RestaurantUser;
import pe.edu.vallegrande.backend.repository.OrderStatusTypeRepository;
import pe.edu.vallegrande.backend.repository.ReservationDetailRepository;
import pe.edu.vallegrande.backend.repository.ReservationRepository;
import pe.edu.vallegrande.backend.repository.TableRepository;
import pe.edu.vallegrande.backend.repository.RestaurantUserRepository;
import pe.edu.vallegrande.backend.service.ReservationService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationDetailRepository detailRepository;
    private final RestaurantUserRepository userRepository;
    private final OrderStatusTypeRepository statusTypeRepository;
    private final TableRepository tableRepository;

    @Autowired
    public ReservationServiceImpl(
            ReservationRepository reservationRepository,
            ReservationDetailRepository detailRepository,
            RestaurantUserRepository userRepository,
            OrderStatusTypeRepository statusTypeRepository,
            TableRepository tableRepository) {
        this.reservationRepository = reservationRepository;
        this.detailRepository = detailRepository;
        this.userRepository = userRepository;
        this.statusTypeRepository = statusTypeRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public List<Reservation> findByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startDate = calendar.getTime();
        
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endDate = calendar.getTime();
        
        return reservationRepository.findByReservationDateBetween(startDate, endDate);
    }

    @Override
    public Reservation createReservation(ReservationRequestDTO request) {
        RestaurantUser user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        OrderStatusType statusType = statusTypeRepository.findById(request.getStatusTypeId())
                .orElseThrow(() -> new RuntimeException("Tipo de estado no encontrado"));
        
        RestaurantTable table = tableRepository.findById(request.getTableId())
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        
        Reservation reservation = new Reservation();
        reservation.setReservationName(request.getReservationName());
        reservation.setReservationDate(request.getReservationDate());
        reservation.setUser(user);
        reservation.setStatusType(statusType);
        
        Reservation savedReservation = reservationRepository.save(reservation);
        
        ReservationDetail detail = new ReservationDetail();
        detail.setReservation(savedReservation);
        detail.setTable(table);
        detail.setNumberPeople(request.getNumberPeople());
        detail.setReservationMethod(request.getReservationMethod());
        detail.setRequest(request.getRequest());
        
        detailRepository.save(detail);
        
        return savedReservation;
    }

    @Override
    public Reservation updateStatus(Long id, Long statusTypeId) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        
        OrderStatusType statusType = statusTypeRepository.findById(statusTypeId)
                .orElseThrow(() -> new RuntimeException("Tipo de estado no encontrado"));
        
        reservation.setStatusType(statusType);
        
        return reservationRepository.save(reservation);
    }
}