package publicTransport;

public class Taxi {
    /*속성*/
    int taxi_passenger = 4;    // 1. 택시의 잔여 승객 수. 기본값 4로 설정
    int taxi_now_passenger;    // 2. 택시 현재 승객수
    int taxi_price;            // 3. 택시 기본 요금
    int taxi_total_amount;     // 4. 해당 택시가 받은 총 금액
    int taxi_number;           // 5. 택시 고유 번호
    int taxi_now_oil = 100;    // 6. 택시 주유량. 기본값 100으로 설정
    int taxi_now_speed;        // 7. 택시 속도
    String taxi_state;         // 8. 택시 현재 상태
    String destination;        // 9. 택시 목적지
    int drive_distance;        // 10. 택시 기본 거리


    /*기능*/

    //생성시에 고유 번호 부여
    public Taxi(int taxi_number) {
        this.taxi_number = taxi_number;
        this.taxi_state = "일반";
    }

    //손님이 택시에 탔을 때
    public void set_passenger(int passenger) {
        //이미 손님이 타있는 택시이거나(운행중) 혹은 주유량이 10미만으로  떨어져 운행불가 상태일 때는 탑승 불가
        if (taxi_state.equals("운행중") || taxi_state.equals("운행불가")) {
            System.out.println("해당 택시를 이용할 수 없습니다. 다른 택시에 탑승하세요.");
            System.out.println("운행 상태 : " + taxi_state);
            System.out.println("-------------------------");
            taxi_now_passenger -= passenger;
            taxi_passenger += passenger;
        }
        taxi_now_passenger += passenger;
        taxi_passenger -= passenger;
        if (taxi_now_passenger == 0 || taxi_now_passenger <= 4) {
            System.out.println(taxi_now_passenger + "명 탑승했습니다.");
            System.out.println("현재 승객수 : " + taxi_now_passenger + "명");
            System.out.println("잔여 승객수 : " + taxi_passenger + "명");
            System.out.println("현재 주유량 : " + taxi_now_oil + "\n");
        } else {
            //잔여 승객수보다 탑승객의 수가 더 많을 때. 즉, 최대 승객수를 초과할 때
            taxi_now_passenger -= passenger;
            taxi_passenger += passenger;
            System.out.println("최대 승객수를 초과합니다. 탑승이 불가합니다.");
            System.out.println("현재 승객수 : " + taxi_now_passenger + "명");
            System.out.println("잔여 승객수 : " + taxi_passenger + "명\n");

        }
    }

    //운행 시작하는 메서드. 목적지와 거리를 매개변수로 받는다.
    public void drive(String destination, int distance) {
        this.destination = destination;
        drive_distance = distance-1;
        taxi_price = 3000;
        //주유량이 10이상일 경우 정상적으로 운행이 가능하다.
        if (taxi_now_oil >= 10 ) {
            taxi_state = "운행중";
            taxi_price += drive_distance *1000;
            System.out.println("운행을 시작합니다.");
            System.out.println("-------------------------");
            System.out.println("현재 승객수 : " + taxi_now_passenger + "명");
            System.out.println("잔여 승객수 : " + taxi_passenger + "명");
            System.out.println("운행 상태 : " + taxi_state);
            System.out.println("기본 요금 : " + 3000 + "원");
            System.out.println("-------------------------");
            System.out.println("목적지 : " + this.destination);
            System.out.println("목적지까지의 거리 : " + distance + "km");
            System.out.println("총 지불할 요금 : " + taxi_price + "\n");
        } else {
            //주유량이 10미만일 경우 운행을 할 수 없다.
            System.out.println("주유량이 부족하여 운행을 할 수 없습니다. 주유가 필요합니다.");
            System.out.println("현재 승객수 : " + taxi_now_passenger + "명");
            System.out.println("잔여 승객수 : " + taxi_passenger + "명");
            System.out.println("현재 주유량 : " + taxi_now_oil + "\n");
        }
    }

    //승객이 돈을 지불할 때
    public void set_payment(int payment) {
        if (taxi_price == payment) {
            //승객이 내리고 택시가 비게 되었으므로 운행 상태 일반으로 전환.
            this.taxi_state = "일반";
            taxi_total_amount += taxi_price;
            taxi_now_passenger = 0;
            taxi_passenger = 4;
            System.out.println("결제가 완료되었습니다. 운행을 종료합니다.");
            System.out.println("운행 상태 : " + taxi_state);
            System.out.println("현재 주유량 : " + taxi_now_oil);
            System.out.println("택시가 번 총 금액 : " + taxi_total_amount + "\n");
        }
        //지불한 금액이 내야할 금액보다 작을 때와 더 많을 때
        if (payment < taxi_price){
            System.out.println("금액이 충분하지 않습니다. 총 지불할 요금을 확인해주세요.");
            System.out.println("지불할 요금 : " + taxi_price + "원\n");
        } else if (payment > taxi_price){
            this.taxi_state = "일반";
            taxi_total_amount += taxi_price;
            taxi_now_passenger = 0;
            taxi_passenger = 4;
            System.out.println("결제가 완료되었습니다. 거스름돈을 챙겨주세요.");
            System.out.println("거스름돈 : " + (payment-taxi_price) + "원");
            System.out.println("현재 택시의 상태 : " + taxi_state);
            System.out.println("현재 주유량 : " + taxi_now_oil);
            System.out.println("택시가 번 총 금액 : " + taxi_total_amount + "\n");
        }
    }

    //주유량 관련 메서드
    public void set_oil(int oil) {
        taxi_now_oil += oil;
        if (taxi_now_oil >= 10) {
            taxi_state = "운행중";
            System.out.println("운행 상태 : " + taxi_state);
            System.out.println("현재 주유량 : " + taxi_now_oil + "\n");
        } else {
            //주유량이 10미만으로 떨어지면 운행 상태를 운행불가로 변경.
            taxi_state = "운행불가";
            System.out.println("주유량이 부족하여 운행을 할 수 없습니다. 주유가 필요합니다.");
            System.out.println("현재 승객수 : " + taxi_now_passenger + "명");
            System.out.println("운행 상태 : " + taxi_state);
            System.out.println("현재 주유량 : " + taxi_now_oil + "\n");
        }
    }

    //택시 속도 관련 메서드 
    public void set_speed(int speed) {
        taxi_now_speed += speed;
        if (taxi_now_oil >= 10) {
            System.out.println("속도가 " + taxi_now_speed + "km/h으로 변경됩니다.\n");
        } else {
            //주유량이 10미만일 경우 속도 변경 불가
            System.out.println("주유량이 부족해 속도를 변경할 수 없습니다.");
            System.out.println("속도 변경을 희망하신다면 주유를 해주세요.");
            System.out.println("현재 주유량 : " + taxi_now_oil + "\n");
            taxi_now_speed -= speed;
        }
    }

    //택시의 현재 정보 출력
    public void show_info() {
        System.out.println("1.택시 번호 : " + taxi_number);
        System.out.println("2.현재 승객수 : " + taxi_now_passenger + "명");
        System.out.println("3.잔여 승객수 : " + taxi_passenger + "명");
        System.out.println("4.운행 상태 : " + taxi_state);
        System.out.println("5.현재 주유량 : " + taxi_now_oil);
        System.out.println("6.현재 속도 : " + taxi_now_speed);
        System.out.println("7.총 받은 금액 : " + taxi_total_amount + "\n");
    }
}
