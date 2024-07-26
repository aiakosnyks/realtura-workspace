// src/components/Toast.js
const Toast = ({ message }) => {
    return (
        <div className="fixed top-0 right-0 m-4 p-2 bg-red-500 text-white rounded">
            {message}
        </div>
    );
};

export default Toast;
