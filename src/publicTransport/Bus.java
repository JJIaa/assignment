package publicTransport;

public class Bus {
    /*속성*/
    int bus_passenger = 30; // 1.버스 잔여 승객 수. 기본값 30으로 설정
    int bus_now_passenger;  // 2.버스 탑승객 수
    int bus_total_amount;   // 3.해당 버스가 받은 총 금액
    int bus_number;         // 4.버스 고유 번호
    int bus_now_oil = 100;  // 5.버스 주유량. 기본값 100으로 설정
    int bus_now_speed;      // 6.버스 현재 속도
    String bus_state;       // 7.버스 현재 상태


    /*기능*/

    //생성 시에 고유 번호 부여
    public Bus(int bus_number) {
        this.bus_number = bus_number;
        this.bus_state = "운행중";
    }

    //버스 상태 변경
    public void set_state(String state) {
        if (state.equals("운행중")) {
            this.bus_state = "운행중";
            System.out.println("운행 상태 : " + bus_state);
            System.out.println("현재 주유량 : " + bus_now_oil + "\n");
        } else if (state.equals("차고지행")) {
            this.bus_state = "차고지행";
            System.out.println("운행 상태 : " + bus_state);
            System.out.println("현재 주유량 : " + bus_now_oil + "\n");
        }
    }

    //승객이 버스에 탔을 때 
    public void set_passenger(int passenger) {
        //버스의 운행 상태가 차고지행일 때는 탑승 불가
        if (bus_state.equals("차고지행")) {
            System.out.println("차고지행인 버스에는 탑승할 수 없습니다.\n");
            bus_now_passenger -= passenger;
            bus_passenger += passenger;
            bus_total_amount -= 1000*passenger;
        }
        bus_now_passenger += passenger;
        bus_passenger -= passenger;
        if (bus_now_passenger == 0 || bus_now_passenger <= 30) {
            bus_total_amount += 1000*passenger;
            System.out.println(passenger + "명 탑승했습니다.");
            System.out.println("현재 승객수 : " + bus_now_passenger + "명");
            System.out.println("잔여 승객수 : " + bus_passenger + "명");
            System.out.println("총 받은 금액 : " + bus_total_amount + "원\n");
        } else {
            //잔여 승객수보다 탑승객의 수가 더 많을 경우. 즉, 최대 승객수를 초과할 경우
            bus_now_passenger -= passenger;
            bus_passenger += passenger;
            System.out.println("최대 승객수를 초과합니다. 탑승이 불가합니다.");
            System.out.println("현재 승객수 : " + bus_now_passenger + "명");
            System.out.println("잔여 승객수 : " + bus_passenger + "명\n");
        }
    }

    //승객이 하차할 때는 버스가 받은 총 금액은 변동이 없고 승객수만 변한다.
    public void out_passenger(int out_passenger) {
        if (out_passenger < 0 || bus_state.equals("차고지행")) {
            bus_now_passenger -= out_passenger;
            bus_passenger += out_passenger;
            System.out.println(out_passenger + "명 하차했습니다.");
            System.out.println("현재 승객수 : " + bus_now_passenger + "명");
            System.out.println("잔여 승객수 : " + bus_passenger + "명");
            System.out.println("총 받은 금액 : " + bus_total_amount + "원\n");
        }
    }

    //버스 주유량 관련 메서드
    public void set_oil(int oil) {
        bus_now_oil += oil;
        if (bus_now_oil >= 10) {
            System.out.println("운행 상태 : " + bus_state);
            System.out.println("현재 주유량 : " + bus_now_oil + "\n");
        } else {
            //주유량이 10미만으로 떨어지면 운행 상태를 차고지행으로 바꾼다.
            this.bus_state = "차고지행";
            System.out.println("주유량이 부족해 운행이 어렵습니다. 주유가 필요합니다.");
            System.out.println("현재 승객수 : " + bus_now_passenger + "명");
            System.out.println("운행 상태 : " + bus_state);
            System.out.println("현재 주유량 : " + bus_now_oil + "\n");
        }
    }

    //버스 속도 관련 메서드
    public void set_speed(int speed) {
        bus_now_speed += speed;
        if (bus_now_oil >= 10) {
            System.out.println("속도가 " + bus_now_speed + "km/h으로 변경됩니다.");
            System.out.println("현재 주유량 : " + bus_now_oil + "\n");
        } else {
            //주유량이 10미만일 경우 속도를 바꿀 수 없다.
            System.out.println("주유량이 부족해 속도를 변경할 수 없습니다.");
            System.out.println("속도 변경을 희망하신다면 주유를 해주세요.");
            System.out.println("현재 주유량 : " + bus_now_oil + "\n");
            bus_now_speed -= speed;
        }
    }

    //버스의 현재 정보 출력
    public void show_info() {
        System.out.println("1.버스 번호 : " + bus_number);
        System.out.println("2.현재 승객수 : " + bus_now_passenger + "명");
        System.out.println("3.잔여 승객수 : " + bus_passenger + "명");
        System.out.println("4.운행 상태 : " + bus_state);
        System.out.println("5.현재 주유량 : " + bus_now_oil);
        System.out.println("6.현재 속도 : " + bus_now_speed + "km/h");
        System.out.println("7.총 받은 금액 : " + bus_total_amount + "원\n");
    }
}
