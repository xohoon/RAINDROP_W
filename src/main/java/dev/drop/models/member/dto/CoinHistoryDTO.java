package dev.drop.models.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CoinHistoryDTO {
    private int member_id;
    private int in_coin;
    private int out_coin;
    private int in_point;
    private int out_point;
    private int in_cash;
    private int out_cash;
    private String in_date;
}
