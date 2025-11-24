# Quick Start Guide - User Experience Features

## 🚀 Getting Started

### 1. Start the Application
```bash
./mvnw spring-boot:run
```

### 2. Access the Application
Open your browser and navigate to: `http://localhost:8090/springdemo`

## 👤 User Features

### Registration
1. Click **"Register"** on the home page
2. Fill in the form:
   - **Username**: Choose a unique username (min 3 characters)
   - **Email**: Enter a valid email address
   - **Full Name**: Your display name (optional)
   - **Password**: Min 6 characters (watch the strength indicator!)
3. Click **"Register"**
4. You'll be redirected to login with a success message

**Password Strength Indicator:**
- 🔴 Very Weak → 🟡 Weak → 🔵 Fair → 🟢 Good → 🟢 Strong
- Tips for strong passwords:
  - Use 10+ characters
  - Mix uppercase and lowercase
  - Include numbers
  - Add special characters

### Login
1. Click **"Login"** on the home page
2. Enter your credentials
3. Check **"Remember me for 7 days"** to stay logged in
4. Click **"Login"**

**Demo Accounts:**
- Admin: `admin` / `admin123`
- User: `user` / `user123`

### Dashboard
After login, you'll see your personalized dashboard with:
- 📊 Deployment statistics
- ✅ Success rate metrics
- ⚙️ Active pipelines
- 📝 Recent activity feed
- 👤 Your account info
- 🔧 Admin panel (admin users only)

**Quick Actions:**
- Click your username → **"Profile"** to edit your profile
- Click **"Edit Profile"** button in the sidebar
- Access H2 Console (admin only)

### Profile Management
Navigate to: **Dashboard → Your Username → Profile**

**What You Can Do:**
1. **Update Email**: Change your email address
2. **Update Full Name**: Change your display name
3. **Change Password**: 
   - Enter new password (min 6 characters)
   - Confirm new password
   - Both fields must match
4. **View Account Info**:
   - Username (cannot be changed)
   - Email
   - Member since date
   - Account status
5. **View Recent Activity**:
   - Last 10 activities
   - Includes login, profile updates, password changes
   - Shows timestamp for each activity

**Profile Update Process:**
1. Edit the fields you want to change
2. Leave password fields blank if you don't want to change password
3. Click **"Save Changes"**
4. Success message will appear
5. Activity will be logged

### Activity Tracking
Your activities are automatically tracked:
- ✅ **LOGIN**: When you log in
- ✅ **USER_REGISTERED**: When you create an account
- ✅ **PROFILE_UPDATED**: When you update your profile
- ✅ **PASSWORD_CHANGED**: When you change your password

**Activity Details Include:**
- Action type
- Description
- IP address
- Browser information
- Timestamp

**View Your Activity:**
- Go to your profile page
- Scroll to "Recent Activity" section
- See your last 10 activities

### Remember Me Feature
When you check "Remember me for 7 days" on login:
- ✅ You stay logged in for 7 days
- ✅ No need to login again when you close the browser
- ✅ Secure token-based authentication
- ✅ Automatically cleared when you logout

## 🎨 UI Features

### Modern Design Elements
- **Gradient Backgrounds**: Purple gradient theme on auth pages
- **Icons**: Font Awesome icons throughout the app
- **Animations**: Smooth transitions and hover effects
- **Responsive**: Works on desktop, tablet, and mobile
- **Cards**: Clean card-based layouts
- **Badges**: Role badges with gradient backgrounds

### Interactive Features
- **Auto-dismissing Alerts**: Success/error messages fade after 5 seconds
- **Loading States**: Buttons show loading spinner during submission
- **Password Strength**: Real-time indicator on registration
- **Password Match**: Visual feedback when passwords don't match
- **Toast Notifications**: Elegant notification system (JavaScript)

### Navigation
- **Navbar**: Consistent across all pages
- **User Dropdown**: Quick access to profile and logout
- **Breadcrumbs**: Easy navigation between pages
- **Mobile Menu**: Responsive hamburger menu

## 🔒 Security Features

### Password Security
- Encrypted with BCrypt
- Minimum 6 characters required
- Strength indicator helps create strong passwords
- Confirmation required for changes

### Session Security
- Secure session management
- Remember-me tokens expire after 7 days
- Logout clears all tokens and sessions
- CSRF protection enabled

### Activity Monitoring
- All security-relevant events logged
- IP address tracking
- Browser information captured
- Helps identify suspicious activity

## 📱 Responsive Design

### Desktop (1200px+)
- Full sidebar layout
- Multi-column dashboard
- Expanded navigation

### Tablet (768px - 1199px)
- Stacked columns
- Collapsible navigation
- Touch-friendly buttons

### Mobile (< 768px)
- Single column layout
- Hamburger menu
- Optimized forms
- Large touch targets

## 🎯 Tips & Tricks

### For Regular Users
1. **Keep Profile Updated**: Update your email and full name for better identification
2. **Use Strong Passwords**: Follow the strength indicator recommendations
3. **Check Activity Log**: Regularly review your activity for security
4. **Use Remember Me**: Save time on trusted devices

### For Admins
1. **Access H2 Console**: View database directly from dashboard
2. **Monitor Users**: Check user activities and registrations
3. **System Health**: Use actuator endpoints for monitoring
4. **API Access**: Use `/api/users` endpoint to list all users

## 🐛 Troubleshooting

### Can't Login?
- ✅ Check username and password are correct
- ✅ Ensure account is enabled
- ✅ Clear browser cache and cookies
- ✅ Try without "Remember me" first

### Profile Update Not Working?
- ✅ Check all required fields are filled
- ✅ Ensure email is not already in use
- ✅ Verify passwords match if changing password
- ✅ Check for validation error messages

### Activity Not Showing?
- ✅ Refresh the page
- ✅ Ensure you're logged in
- ✅ Check H2 console for data
- ✅ Review application logs

### Remember Me Not Working?
- ✅ Ensure cookies are enabled in browser
- ✅ Check you clicked the checkbox
- ✅ Don't use incognito/private mode
- ✅ Token expires after 7 days

## 📊 API Endpoints

### Public API
```bash
# Check application status
curl http://localhost:8090/springdemo/api/status
```

### Authenticated API
```bash
# Get current user info (requires login)
curl -u username:password http://localhost:8090/springdemo/api/user/me
```

### Admin API
```bash
# List all users (requires ADMIN role)
curl -u admin:admin123 http://localhost:8090/springdemo/api/users
```

## 🎓 Learning Resources

### Spring Security
- [Official Documentation](https://spring.io/projects/spring-security)
- Remember Me: Token-based authentication
- UserDetailsService: Custom user loading

### Thymeleaf
- [Official Documentation](https://www.thymeleaf.org/)
- Spring Security Integration
- Template fragments

### Bootstrap
- [Bootstrap 4 Documentation](https://getbootstrap.com/docs/4.6/)
- Responsive grid system
- Components and utilities

## 📞 Support

### Need Help?
1. Check the logs: `logs/spring-boot-application.log`
2. Review H2 Console: `http://localhost:8090/springdemo/h2-console`
3. Check actuator health: `http://localhost:8090/springdemo/actuator/health`
4. Open an issue on GitHub

### Common Questions

**Q: Can I change my username?**
A: No, usernames are permanent. Create a new account if needed.

**Q: How long do sessions last?**
A: Without "Remember me": Until browser closes. With "Remember me": 7 days.

**Q: Can I delete my account?**
A: Currently not implemented. Contact admin for account deletion.

**Q: Is my password secure?**
A: Yes, passwords are encrypted with BCrypt and never stored in plain text.

**Q: Can I see all my activities?**
A: Currently shows last 10. Full history feature coming soon.

---

**Enjoy your enhanced user experience! 🎉**
