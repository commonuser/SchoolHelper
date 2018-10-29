package cn.edu.hytc.schoolhelper.http.jsonentity;
import java.util.List;
public class CourseJsonEntity {

    private List<KbListBean> kbList;

    public List<KbListBean> getKbList() {
        return kbList;
    }

    public void setKbList(List<KbListBean> kbList) {
        this.kbList = kbList;
    }

    public static class KbListBean {

        private String cd_id;
        private String cdmc;


        public String getCd_id() {
            return cd_id;
        }

        public void setCd_id(String cd_id) {
            this.cd_id = cd_id;
        }

        public String getCdmc() {
            return cdmc;
        }

        public void setCdmc(String cdmc) {
            this.cdmc = cdmc;
        }

    }
}
