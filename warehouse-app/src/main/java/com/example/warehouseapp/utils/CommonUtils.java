package com.example.warehouseapp.utils;


import com.example.warehouseapp.exception.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;

public class CommonUtils {
    public static void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("Sahifa soni noldan kam bo'lishi mumkin emas.");
        }

        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Sahifa soni " + AppConstants.MAX_PAGE_SIZE + " dan ko'p bo'lishi mumkin emas.");
        }
    }

    public static Pageable getPageable(int page, int size, String byField) {
        validatePageNumberAndSize(page, size);
        return PageRequest.of(page, size, Sort.Direction.DESC, byField);
    }

    public static Pageable getPageable(int page, int size) {
        validatePageNumberAndSize(page, size);
        return PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
    }

    public static Pageable getPageableWithoutSort(int page, int size) {
        validatePageNumberAndSize(page, size);
        return PageRequest.of(page, size);
    }

    public static Pageable getPageableForNative(int page, int size) {
        validatePageNumberAndSize(page, size);
        return PageRequest.of(page, size, Sort.Direction.DESC, "created_at");
    }

    public static Timestamp validTimestamp(Timestamp timestamp, Boolean isFrom) {
        if (isFrom)
            return timestamp == null ? new Timestamp(1) : timestamp;
        return timestamp == null ? new Timestamp(System.currentTimeMillis()) : timestamp;
    }
}
