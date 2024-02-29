document.addEventListener('DOMContentLoaded', function () {
    // Get the checkbox and payment method radio button elements
    var checkbox = document.querySelector('.check_terms_condition');
    var paymentMethodRadio = document.querySelector('.payment_method_radio');

    // Get the payment and cancel buttons
    var paymentButton = document.getElementById('payment-next');
    var cancelPaymentButton = document.getElementById('payment-back');

    // Add an event listener to the payment button
    paymentButton.addEventListener('click', function (event) {
        // Check if the checkbox is not checked
        if (!checkbox.checked) {
            // Display an alert message
            alert('Vui lòng đảm bảo bạn đã đồng ý với điều khoản và điều kiện!');
            // Prevent the form from submitting
            event.preventDefault();
        } else if (!paymentMethodRadio.checked) {
            // Check if no payment method is selected
            alert('Vui lòng chọn phương thức thanh toán!');
            // Prevent the form from submitting
            event.preventDefault();
        } else {
            // The conditions are met, the form will submit and redirect to the specified link
        }
    });

    // Add an event listener to the cancel button
    cancelPaymentButton.addEventListener('click', function () {
        // Redirect to the cancel link
        window.location.href = cancelPaymentButton.getAttribute('data-link');
    });
});
