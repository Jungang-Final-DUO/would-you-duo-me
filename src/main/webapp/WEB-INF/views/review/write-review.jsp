<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="review-modal-background">
    <div id="review-modal-wrapper">
        <div id="review-modal-title">
            <input type="hidden" name = "matchingNo" value = "">
            <div id = "profile-image-frame"><img id = "profile-image" src="/assets/img/chattingModal/young.jpg" alt="profile"></div>
            <div id="nickname-rate">
                <div id = "user-nickname"></div>
                <div id = "give-rate">
                    <img id = "rate1" class = "reviewRate rate-image" src="/assets/img/chattingModal/rate-filled.png" alt = "rate">
                    <img id = "rate2" class = "reviewRate rate-image" src="/assets/img/chattingModal/rate-filled.png" alt = "rate">
                    <img id = "rate3" class = "reviewRate rate-image" src="/assets/img/chattingModal/rate-filled.png" alt = "rate">
                    <img id = "rate4" class = "reviewRate rate-image" src="/assets/img/chattingModal/rate-filled.png" alt = "rate">
                    <img id = "rate5" class = "reviewRate rate-image" src="/assets/img/chattingModal/rate-filled.png" alt = "rate">
                </div>
            </div>
        </div>
        <div id="review-modal-content">
            <label><textarea id = "reviewContent" name = "reviewContent" placeholder="게임은 어떠셨나요?&#10;솔직한 후기를 작성해주세요"></textarea><img id = "send-review" src="/assets/img/chattingModal/send-review.png" alt="send-review"></label>
        </div>
    </div>
</div>