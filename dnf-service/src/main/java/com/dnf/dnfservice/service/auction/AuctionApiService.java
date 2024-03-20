package com.dnf.dnfservice.service.auction;

import com.dnf.dnfservice.dto.feign.auction.AuctionSearchDto;
import com.dnf.dnfservice.dto.feign.auction.AuctionSoldDto;

public interface AuctionApiService {
	AuctionSearchDto searchAuctionItems(String itemName);

	AuctionSearchDto searchByItemId(String itemId);

	AuctionSoldDto getSoldHistory(String itemId);
}
