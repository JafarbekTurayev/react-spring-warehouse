package com.example.warehouseapp.bot;

import com.example.warehouseapp.entity.BotUser;
import com.example.warehouseapp.repository.BotUserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
public class WarehouseBot extends TelegramLongPollingBot {

    @Value("${bot.token}")
    String token;
    @Value("${bot.username}")
    String username;

    @Autowired
    TelegramServiceImpl telegramService;

    @Autowired
    BotUserRepository botUserRepository;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            String text = update.getMessage().getText();
            String chatId = String.valueOf(update.getMessage().getChatId());
            if (message.hasText()) {
                if (text.equals("/start")) {
                    BotUser botUser = new BotUser();
                    botUser.setChatId(String.valueOf(update.getMessage().getChatId()));
                    botUser.setState(BotState.CHOOSE_LANG);
                    botUserRepository.save(botUser);
                    execute(telegramService.welcome(update));
                } else {
                    Optional<BotUser> optionalBotUser = botUserRepository.findByChatIdAndActiveTrue(chatId);
                    if (optionalBotUser.isPresent()) {
                        BotUser botUser = optionalBotUser.get();
                        String state = botUser.getState();
                        switch (state) {
                            case BotState.CHOOSE_LANG:
                                if (text.equals(Constant.BUTTON_UZ)) botUser.setLang(Constant.LANG_UZ);
                                else botUser.setLang(Constant.LANG_RU);
                                botUser.setState(BotState.SHARE_CONTACT);
                                botUserRepository.save(botUser);
                                execute(telegramService.shareContact(update));
                                break;
                            case BotState.MAIN_MENU:
                                switch (text) {
                                    case Constant.WAREHOUSE_TEXT_RU:
                                    case Constant.WAREHOUSE_TEXT_UZ:
                                        botUser.setState(BotState.WAREHOUSE_LIST);
                                        execute(telegramService.selectWarehouse(update));
                                        break;
                                    case Constant.SETTING_TEXT_UZ:
                                    case Constant.SETTING_TEXT_RU:
                                        botUser.setState(BotState.SETTINGS);
                                        execute(telegramService.settings(update));
                                        break;
                                    case Constant.HELP_TEXT_UZ:
                                    case Constant.HELP_TEXT_RU:
                                        botUser.setState(BotState.HELP);
                                        execute(telegramService.help(update));
                                        break;
                                }
                                botUserRepository.save(botUser);
                                break;
                            case BotState.WAREHOUSE_LIST:
                                botUser.setState(BotState.MENU_PRODUCTS);
                                botUserRepository.save(botUser);
                                execute(telegramService.menuProducts(update));
                                break;
                            case BotState.MENU_PRODUCTS:
                                botUser.setState(BotState.ONE_PRODUCT);
                                botUserRepository.save(botUser);
                                execute(telegramService.oneProduct(update));
                                break;
                        }
                    }
                }
            } else if (message.hasContact()) {
                Optional<BotUser> optionalBotUser = botUserRepository.findByChatIdAndActiveTrue(chatId);
                if (optionalBotUser.isPresent()) {
                    BotUser botUser = optionalBotUser.get();
                    String state = botUser.getState();
                    switch (state) {
                        case BotState.SHARE_CONTACT:
                            botUser.setState(BotState.SHARE_LOCATION);
                            botUserRepository.save(botUser);
                            execute(telegramService.shareLocation(update));
                            break;
                    }
                }
            } else if (message.hasLocation()) {
                Location location = update.getMessage().getLocation();
                Optional<BotUser> optionalBotUser = botUserRepository.findByChatIdAndActiveTrue(chatId);
                if (optionalBotUser.isPresent()) {
                    BotUser botUser = optionalBotUser.get();
                    String state = botUser.getState();
                    switch (state) {
                        case BotState.SHARE_LOCATION:
                            botUser.setLat(location.getLatitude());
                            botUser.setLon(location.getLongitude());
                            botUser.setState(BotState.MAIN_MENU);
                            botUserRepository.save(botUser);
                            execute(telegramService.mainMenu(update));
                            break;
                    }
                }
            }
        } else if (update.hasCallbackQuery()) {

            if (update.getCallbackQuery().getData().equals("product")) {

                String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
                System.out.println("Keldi!" + chatId);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

}
