package com.devchris.admin.ifs;

import com.devchris.admin.model.network.Header;

public interface CrudInterface<ApiRequest, ApiResponse> {
    Header<ApiResponse> create(Header<ApiRequest> ApiRequest);
    Header<ApiResponse> read(Long id);
    Header<ApiResponse> update(Header<ApiRequest> ApiRequest);
    Header delete(Long id);
}
