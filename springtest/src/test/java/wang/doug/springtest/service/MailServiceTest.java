package wang.doug.springtest.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    @Autowired
    MailService mailService;
    @Test
    public void sendSimpleMail() {
        // 3319184157@qq.com
        for (int i = 0; i<20;i++){
            mailService.sendSimpleMail("3319184157@qq.com","甘肃交警提示","【甘肃交警】吴华东先生您由于长期打王者荣耀不去练车现已取消你的考试资格且三年内不得考驾照！特此通知！");
        }
        System.out.println("-----------------succeed----------------");
    }
}
