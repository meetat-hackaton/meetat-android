package util;

import java.util.LinkedList;
import java.util.List;

import fr.tkeunebr.gravatar.Gravatar;

/**
 * Created by raina on 13.03.2016.
 */
public class SimpledummyData {
    public static class Row{
        String name;
        Row(String name, String avatar){
            this.avatar = avatar;
            this.name = name;
        }
        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        String avatar;
    }

    public static List<Row> rows;
    static {
        rows = new LinkedList<>();
        String gravatarUrl = Gravatar.init().with("mahieke@htwg-konstanz.de").size(200).build();
        rows.add(new Row("Manuel", gravatarUrl));
        String gravatarUrlP = Gravatar.init().with("philipp.daniels@gmail.com").size(200).build();
        rows.add(new Row("Philipp", gravatarUrlP));
        String gravatarUrlI = Gravatar.init().with("raina.bertolini@gmail.com").size(200).build();
        rows.add(new Row("Ína", gravatarUrlI));
    }
}
