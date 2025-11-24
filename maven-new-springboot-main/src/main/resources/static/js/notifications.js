// Toast Notification System
function showToast(message, type = 'info') {
    const toastContainer = $('#toast-container');
    
    // Create container if it doesn't exist
    if (toastContainer.length === 0) {
        $('body').append('<div id="toast-container" style="position: fixed; top: 20px; right: 20px; z-index: 9999;"></div>');
    }
    
    const icons = {
        success: 'fa-check-circle',
        error: 'fa-exclamation-circle',
        warning: 'fa-exclamation-triangle',
        info: 'fa-info-circle'
    };
    
    const colors = {
        success: '#28a745',
        error: '#dc3545',
        warning: '#ffc107',
        info: '#17a2b8'
    };
    
    const toast = $(`
        <div class="toast-notification" style="
            background: white;
            border-left: 4px solid ${colors[type]};
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            padding: 16px 20px;
            margin-bottom: 10px;
            min-width: 300px;
            max-width: 400px;
            display: flex;
            align-items: center;
            animation: slideIn 0.3s ease-out;
        ">
            <i class="fas ${icons[type]}" style="color: ${colors[type]}; font-size: 20px; margin-right: 12px;"></i>
            <span style="flex: 1; color: #333;">${message}</span>
            <button class="toast-close" style="
                background: none;
                border: none;
                color: #999;
                font-size: 20px;
                cursor: pointer;
                padding: 0;
                margin-left: 10px;
            ">&times;</button>
        </div>
    `);
    
    $('#toast-container').append(toast);
    
    // Close button
    toast.find('.toast-close').on('click', function() {
        toast.fadeOut(300, function() { $(this).remove(); });
    });
    
    // Auto-hide after 5 seconds
    setTimeout(function() {
        toast.fadeOut(300, function() { $(this).remove(); });
    }, 5000);
}

// Add animation keyframes
if (!$('#toast-animations').length) {
    $('head').append(`
        <style id="toast-animations">
            @keyframes slideIn {
                from {
                    transform: translateX(400px);
                    opacity: 0;
                }
                to {
                    transform: translateX(0);
                    opacity: 1;
                }
            }
        </style>
    `);
}
