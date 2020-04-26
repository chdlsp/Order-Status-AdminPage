package com.devchris.admin.service;

import com.devchris.admin.ifs.CrudInterface;
import com.devchris.admin.model.entity.Item;
import com.devchris.admin.model.network.Header;
import com.devchris.admin.model.network.request.ItemApiRequest;
import com.devchris.admin.model.network.response.ItemApiResponse;
import com.devchris.admin.repository.ItemRepository;
import com.devchris.admin.repository.PartnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> ApiRequest) {

        ItemApiRequest body = ApiRequest.getData();

        Item item = Item.builder()
                .status(body.getStatus())
                .name(body.getName())
                .title(body.getTitle())
                .content(body.getContent())
                .price(body.getPrice())
                .brandName(body.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getOne(body.getPartnerId()))
                .build();

        Item newItem = itemRepository.save(item);

        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        Optional<Item> readItem = itemRepository.findById(id);
        log.info("readItem : {}", readItem);

        return readItem
                .map(item -> response(item))
                .orElseGet(()-> Header.ERROR("Data Not Exists"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> ApiRequest) {

        ItemApiRequest body = ApiRequest.getData();
        Optional<Item> readItem = itemRepository.findById(body.getId());

        return readItem.map(item-> {
                        item.setStatus(body.getStatus())
                            .setName(body.getName())
                            .setTitle(body.getTitle())
                            .setContent(body.getContent())
                            .setPrice(body.getPrice())
                            .setBrandName(body.getBrandName())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt());
                    return item;
                })
                .map(newReadItem-> itemRepository.save(newReadItem))
                .map(returnItem->response(returnItem))
                .orElseGet(()->Header.ERROR("Data Not Exists"));
    }

    @Override
    public Header delete(Long id) {

        Optional<Item> readItem = itemRepository.findById(id);

        return readItem.map(item-> {
                    itemRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("Data Not Exists"));

    }

    private Header<ItemApiResponse> response(Item item) {

        // TODO : Enum 설명 수정 : String statusTitle = item.getStatus().getDescription();

        ItemApiResponse body = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();

        return Header.OK(body);
    }
}
