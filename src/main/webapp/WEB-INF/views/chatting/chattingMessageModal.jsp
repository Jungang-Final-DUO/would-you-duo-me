<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--    <div class="modal-background show-modal"></div>--%>
    <div class="chatting-message-modal-wrapper show-chatting-message-modal">
        <div class="chatting-message-modal-container">
            <div class="chatting-message-head">
                <img class="chatting-arrow-img" src="/assets/img/chattingModal/arrows.png" alt="뒤로가기">
                <div class="chatting-message-nickname">HongChaa</div>
            </div>
            <div class="chatting-message-option">
                <div class="matching-accept-container">
                    <img class="chatting-handshake-img" src="/assets/img/chattingModal/handshake.png" alt="매칭수락이미지">
                    <button class="matching-accept-btn">매칭 확정</button>
                </div>
                <div class="gameover-container">
                    <img class="chatting-gameover-img" src="/assets/img/chattingModal/checkmark.png" alt="게임완료이미지">
                    <button class="gameover-btn">게임 완료</button>
                </div>
            </div>
            <div class="chatting-message-body">
                <div class="chatting-message-card message-to">
                    <img class="chatting-profile" src="/assets/img/chattingModal/woogi.jpg" alt="프로필이미지">
                    <div class="message-content">안녕하세요!!</div>
                </div>
<%--                <div class="chatting-message-card message-from">--%>
<%--                    <div class="message-content">반갑습니다!!!</div>--%>
<%--                    <img class="chatting-profile" src="/assets/img/chattingModal/young.jpg" alt="프로필이미지">--%>
<%--                </div>--%>
<%--                <div class="chatting-message-card message-to">--%>
<%--                    <img class="chatting-profile" src="/assets/img/chattingModal/woogi.jpg" alt="프로필이미지">--%>
<%--                    <div class="message-content">안녕하세요!!</div>--%>
<%--                </div>--%>
<%--                <div class="chatting-message-card message-from">--%>
<%--                    <div class="message-content">반갑습니다!!!</div>--%>
<%--                    <img class="chatting-profile" src="/assets/img/chattingModal/young.jpg" alt="프로필이미지">--%>
<%--                </div>--%>
<%--                <div class="chatting-message-card message-to">--%>
<%--                    <img class="chatting-profile" src="/assets/img/chattingModal/woogi.jpg" alt="프로필이미지">--%>
<%--                    <div class="message-content">안녕하세요!!</div>--%>
<%--                </div>--%>
<%--                <div class="chatting-message-card message-from">--%>
<%--                    <div class="message-content">반갑습니다!!!</div>--%>
<%--                    <img class="chatting-profile" src="/assets/img/chattingModal/young.jpg" alt="프로필이미지">--%>
<%--                </div>--%>
<%--                <div class="chatting-message-card message-to">--%>
<%--                    <img class="chatting-profile" src="/assets/img/chattingModal/woogi.jpg" alt="프로필이미지">--%>
<%--                    <div class="message-content">안녕하세요!!</div>--%>
<%--                </div>--%>
<%--                <div class="chatting-message-card message-from">--%>
<%--                    <div class="message-content">반갑습니다!!!</div>--%>
<%--                    <img class="chatting-profile" src="/assets/img/chattingModal/young.jpg" alt="프로필이미지">--%>
<%--                </div>--%>
            </div>
            <form id = "chat-form" class="chatting-message-input-box">
                <input id = "msg" class="message-send-box" type="text" placeholder="메시지를 입력해주세요" required>
                <button class="message-send-btn">전송</button>
            </form>
        </div>
    </div>