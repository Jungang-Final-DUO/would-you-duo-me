package site.woulduduo.dto.request.page;

import lombok.*;
import site.woulduduo.enumeration.AdminViewType;
@Setter
@Getter
@ToString

public class AdminSearchType extends PageDTO{
    AdminViewType adminViewType;

    //today 로 기본값 설정
    public AdminSearchType(){
        this.adminViewType= AdminViewType.TODAY;
    }

}
