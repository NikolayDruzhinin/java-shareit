package ru.practicum.shareit.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.shareit.item.dto.RequestWithItemsProjection;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.List;

public interface ItemRequestRepository extends JpaRepository<ItemRequest, Long> {

    @Query("SELECT ir as request, i as item, u.id as ownerId " +
            "FROM ItemRequest ir " +
            "LEFT JOIN Item i ON i.request.id = ir.id " +
            "LEFT JOIN User u ON i.owner.id = u.id " +
            "WHERE ir.id = :requestId " +
            "ORDER BY ir.createdAt DESC")
    List<RequestWithItemsProjection> findRequestsWithItemsAndOwnersByRequestId(@Param("requestId") Long requestId);

    List<ItemRequest> findByUser_Id(Long requesterId);
}
