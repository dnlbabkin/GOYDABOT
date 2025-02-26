package goydabot.example.GOYDABot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

@Component
public class GoydaBotService extends TelegramLongPollingBot {

    private final String BOT_TOKEN = "7693052577:AAEFc1oDgvMVwGTnkh1I_gp1OY4_dM5MHUs";
    private final String BOT_USERNAME = "GoyydaaBot";
    private final String[] STICKER_IDS = {"CAACAgIAAxkBAAEM8xlnvvkVDT9Dnu43mD_J2Nvict0caQACJEMAAq0zaUnfWMjlHjHDpjYE",
            "CAACAgIAAxkBAAEM8xtnvvkYen31ahRtEB2CJjFH1UfeHgACT0kAAjf3aEmXVMGTmF1VPzYE",
            "CAACAgIAAxkBAAEM8x1nvvkaimVmAlLFQQe8fzctPPmOygAC5kYAAkTGaEnW97nG4N1ItDYE",
            "CAACAgIAAxkBAAEM8x9nvvkcD_iJvNPAriisRmbjRa20fAAC0EYAAkCHaUmu5yuFI3rt-zYE",
            "CAACAgIAAxkBAAEM8yFnvvkeFMS3wwK7zZhkcf5DAQSgpQAChUIAAqeQaUnzAwOaZmg5qzYE",
            "CAACAgIAAxkBAAEM8yNnvvkff-RDJDs6f-1FWy_ZMPifPwACn0gAAmzYaEl3kl5hvlhIcTYE",
            "CAACAgIAAxkBAAEM8yVnvvkh9EMAAc8s-JtLddGk2asKgwQAAglFAALG32lJGxQZ3R5ANW02BA",
            "CAACAgIAAxkBAAEM8ydnvvkiI3MnppobnJiTAn-naRq6NQAC8kUAAkqkaUk-sLMdR4D96jYE",
            "CAACAgIAAxkBAAEM8ylnvvklvvogHgUfxI141vAxavxtOAACMVMAAge5YEmQzkKHEoaeVTYE",
            "CAACAgIAAxkBAAEM8ytnvvkmv9Y93erYdMgV2csHraxdJAACuUsAAmW3aUlcs0Oehox1ZDYE",
            "CAACAgIAAxkBAAEM8y1nvvkoCxiyobUq_Bsb6LHmD-UZpAACYkoAAn4MaUkDRmKJfqUsGDYE",
            "CAACAgIAAxkBAAEM8y9nvvkqydlwq_87sgABA6s0flbScc4AAtRKAAKt32hJvD5ldc7zx5w2BA",
            "CAACAgIAAxkBAAEM8zFnvvksLx5dOUUj9Vnv1zkDxotqWgAC2EQAAknpaUkhWSlU6cmd6DYE",
            "CAACAgIAAxkBAAEM8zNnvvkv5ZhhUHX-99P2eGCzdxIRPgACKEcAAvnZaUn2gfhDpRkXozYE",
            "CAACAgIAAxkBAAEM8zVnvvkwoOq_tNU944DHVaTKErhgEAACUU4AAg5EaEktcTVuT5Ey9jYE",
            "CAACAgIAAxkBAAEM8zdnvvky75QjPbbBowQIOj7eFpmNlAACq08AAt6faUkbmexGD0v73TYE",
            "CAACAgIAAxkBAAEM8zlnvvkzVPB2rDLUuSl8BPHMKOu_bgACyUwAAno1aEnroHR2tOUInDYE",
            "CAACAgIAAxkBAAEM8ztnvvk0DliqF2S66JJOCHw-xg-eIQACHEMAAtvYaEmhXWp6NVprdjYE",
            "CAACAgIAAxkBAAEM8z1nvvk3ReOCbx5d7mayE9iILVdi5gAC_EgAAoQ0aUk6cnmf8CtdCzYE",
            "CAACAgIAAxkBAAEM8z9nvvk4z3D_EBP-TV-R4Y2NKZSgAAM7RAACdxNoSQABxBc4oOtD0zYE",
            "CAACAgIAAxkBAAEM80Fnvvk5Bsp952ZbThQ-y9W3lRglDgACxUgAAqilcEl4L8h87uwbpTYE",
            "CAACAgIAAxkBAAEM80Nnvvk8X3IaDrLn6iZRmdRiktUmFgACVkYAAvR1cEm3duvata47_DYE",
            "CAACAgIAAxkBAAEM80Vnvvk9B3PZcNnkqCpg0-FAlKz4QgACIkcAAiGMcUmjnh14eK9BKjYE",
            "CAACAgIAAxkBAAEM80dnvvk_AAE4CbOaZ7q_qOR-kktYRkEAAvBFAALSU3FJIJXXHIq5GuI2BA",
            "CAACAgIAAxkBAAEM80lnvvlAgA5SAAFkoZLXeQ53g9Wans4AArlKAAI2hnFJnSlFuaffXOw2BA",
            "CAACAgIAAxkBAAEM80tnvvlCC3a1sRIG5Jv0Sj1-yhcZ2AACQUkAAipjcUmsHlCzucYo2jYE",
            "CAACAgIAAxkBAAEM801nvvlDI5AyhsSSwkRKdxGc8s54RQACdkgAAnlVcUkmY78yPpm9dTYE",
            "CAACAgIAAxkBAAEM809nvvlF7BATOcjaLJ5Kfc5769WGlgACHE4AAi-ncUlrhivju1K9CDYE",
            "CAACAgIAAxkBAAEM81FnvvlHi44ZBfnE0O-4YH3EJLKtpwAChUsAAjbleUlcxsYI4vfRQzYE",
            "CAACAgIAAxkBAAEM81NnvvlIyRq-I9BkgVlSMdPxkg8KCgACw0kAAs63eEmApikBmgu3SDYE",
            "CAACAgIAAxkBAAEM81VnvvlKDxlzH5yoS0zyrqVWQCtX_gACl1EAAgiacEmztthc3AJzHDYE",
            "CAACAgIAAxkBAAEM81dnvvlNwukZpEdfIE_w74s22yhiHwACWEoAAiqTeEmyPgmB3QfsoTYE",
            "CAACAgIAAxkBAAEM81lnvvlOO-9nCwX1TGCsr4253hYTfgACmU0AAj6pcUlQAvfs-YyOkDYE",
            "CAACAgIAAxkBAAEM81tnvvlPDQvSl0Lfv90qctHk6CHPOAAC70YAAskveUnKWsnlYvpGczYE",
            "CAACAgIAAxkBAAEM811nvvlQGjr9eNbrhesgwQeeThPUCwACikwAAkE6eUlunVew5WArGDYE",
            "CAACAgIAAxkBAAEM819nvvlRQY6nmw6Gjtqj7n2VsWDrdQACp0gAAqjygEl2-_Z7VP8nbDYE",
            "CAACAgIAAxkBAAEM82FnvvlSiXsE7mgg2SgpI6WfVa5B0QACBkgAAgqWgEl7K50wC7U4ejYE",
            "CAACAgIAAxkBAAEM82NnvvlTu4usGZ-vWis-tc9FgzOS-gACyk8AAuPdgEl5_irVEyiGCTYE",
            "CAACAgIAAxkBAAEM82VnvvlUEtT5ShfL2ocx1GH5lzu2NwADUAACUBJ5Sbymcnkz67ptNgQ",
            "CAACAgIAAxkBAAEM82dnvvlVNirATk1_zGITERpGpEnbEgACP0kAAkMRgEmlxQnmWDiPLjYE",
            "CAACAgIAAxkBAAEM82lnvvlWye_9vWjElTCypTUuTQJugwACfEgAAun5gUkzPBxqj91SBTYE",
            "CAACAgIAAxkBAAEM82tnvvlXGigtxhtEvmCb372BmZEs1gACh0wAAlrHgEmGaCo-zPsJ8zYE",
            "CAACAgIAAxkBAAEM821nvvleX4lGmRSwECZHNKuwRgjs3gACBUwAApkkgUlFZEORil8OyjYE",
            "CAACAgIAAxkBAAEM83FnvvlhZrvBrnYo2zV6e7KFuoyyTgACMlAAAn1rgUn0_8xSkr--IDYE",
            "CAACAgIAAxkBAAEM83Vnvvljkzk3y7h5sIpfApZp4YgYkAACPFUAAm8ogUnqMy77xgpKiTYE",
            "CAACAgIAAxkBAAEM83dnvvll6z6HxmbQNxOlWWhTPbxWiQACHEsAArCqqElwRDRlSeJqgzYE",
            "CAACAgIAAxkBAAEM83tnvvloOP1-VRpJ1eFHyAKeCEMlTgACOE8AAk_vqEk7MrQ07qQPhTYE",
            "CAACAgIAAxkBAAEM831nvvlqQEGSlm5OmuEjchHxsWfPtgAChlAAAsrMqEkBaVrNmoYOrDYE",
            "CAACAgIAAxkBAAEM839nvvlr-MsB9uh0QXSLbhcQ8m7pagAC7EMAAug7sUkC_RCfMAv2izYE",
            "CAACAgIAAxkBAAEM84FnvvltVBpDdS6C_MYT-zZs5I0BEAACnk4AAtAvqElCEw4GiAsRrTYE",
            "CAACAgIAAxkBAAEM84NnvvluYeqDqwrM--DlCS063yQuxwACfksAAm4PqUmnDqxS40zPbzYE"
    };


    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText().toLowerCase();
            long chatId = update.getMessage().getChatId();

            if (messageText.contains("ГОЙДА") || messageText.contains("гойда") || messageText.contains("Гойда")) {
                sendRandomSticker(chatId);
            }
        }
    }

    private void sendRandomSticker(long chatId) {
        Random random = new Random();
        String randomStickerId = STICKER_IDS[random.nextInt(STICKER_IDS.length)];

        SendSticker sendSticker = new SendSticker();
        sendSticker.setChatId(String.valueOf(chatId));
        sendSticker.setSticker(new InputFile(randomStickerId));

        try {
            execute(sendSticker);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
