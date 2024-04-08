import { React } from "react";
import { useForm } from "react-hook-form"
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import apiService from "../apiService";
import 'bootstrap/dist/css/bootstrap.css';
import Navigation from "./navBar";

/**
 * Component for administering user operations such as add, delete, and update.
 */
const AdminPortal = () => {

    const { register, formState: { errors }, handleSubmit, setError } = useForm();

    /**
     * Handles adding a user.
     * @param user User data from form.
     */
    const handleAddUser = (user) => {
        apiService.addUser(user).then(response => {
            console.log(response);
            alert(response.message)
        }).catch(error => {
            console.log(error)
            alert(error)
        })
    }

    /**
     * Handles deleting a user.
     * @param user User data from form.
     */
    const handleDeleteUser = (user) => {
        apiService.deleteUser(user).then(response => {
            console.log(response);
            alert(response.message)
        }).catch(error => {
            console.log(error)
            alert(error)
        })
    }

    /**
     * Handles updating a user.
     * @param user User data from form.
     */
    const handleEditUser = (user) => {
        apiService.editUser(user).then(response => {
            console.log(response);
            alert(response.message)
        }).catch(error => {
            console.log(error)
            alert(error)
        })
    }

    /**
     * Submits the form based on the action type (add, delete, update).
     * @param data Form data.
     * @param action Type of action to perform.
     */
    const handleFormSubmit = (data, action) => {
        if (action === 'add') {
            handleAddUser(data);
        } else if (action === 'delete') {
            if (!data.id) {
                setError('id', { type: 'manual', message: 'ID is required for deletion.' });
                return;
            }
            handleDeleteUser(data);
        } else if (action === 'update') {
            handleEditUser(data);
        }
    };

    return (
        <>
            <Navigation />
            <Container className="mt-4">
                <h1>Admin Portal: User Management</h1>

                <Form onSubmit={handleSubmit((data) => handleFormSubmit(data, 'add'))}> {/* Default action */}
                    <Form.Group className="mb-3">
                        <Form.Label htmlFor="id"><h4>ID (Required for Deleting User And Updating User Names)</h4></Form.Label>
                        <Form.Control
                            id="id"
                            type="number" // Ensures only numerical input
                            className="thick-border"
                            {...register('id', {
                                valueAsNumber: true, // Ensures the value is treated as a number
                            })}
                            isInvalid={!!errors.id}
                        />
                        <Form.Control.Feedback type="invalid">
                            {errors.id?.message}
                        </Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label htmlFor="firstName"><h4>First Name</h4></Form.Label>
                        <Form.Control
                            className="thick-border"
                            id="firstName"
                            type="text"
                            {...register('firstName', {
                                required: 'First name is required',
                                pattern: {
                                    value: /^[A-Za-z ]+$/i, // Regex pattern to match only letters
                                    message: 'First name must contain only letters and spaces'
                                },
                                minLength: {
                                    value: 2,
                                    message: 'First name must be at least 2 characters long'
                                }
                            })}
                            isInvalid={!!errors.firstName}
                        />
                        <Form.Control.Feedback type="invalid">
                            {errors.firstName?.message}
                        </Form.Control.Feedback>
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label htmlFor="lastName"><h4>Last Name</h4></Form.Label>
                        <Form.Control
                            className="thick-border"
                            id="lastName"
                            type="text"
                            {...register('lastName', {
                                required: 'Last name is required',
                                pattern: {
                                    value: /^[A-Za-z ]+$/i, // Regex pattern to match only letters
                                    message: 'First name must contain only letters and spaces'
                                },
                                minLength: {
                                    value: 2,
                                    message: 'Last name must be at least 2 characters long'
                                }
                            })}
                            isInvalid={!!errors.lastName}
                        />
                        <Form.Control.Feedback type="invalid">
                            {errors.lastName?.message}
                        </Form.Control.Feedback>
                    </Form.Group>

                    {/* Shape and Color Select Inputs */}
                    <Form.Group className="mb-3">
                        <Form.Label htmlFor="shape"><h4>Shape</h4></Form.Label>
                        <Form.Select id="shape" {...register('shape', { required: true })}>
                            <option value={null}>(deselect)</option>
                            <option value="Circle">Circle</option>
                            <option value="Triangle">Triangle</option>
                            <option value="Square">Square</option>
                        </Form.Select>
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label htmlFor="color"><h4>Color</h4></Form.Label>
                        <Form.Select id="color" {...register('color', { required: true })}>
                            <option value={null}>(deselect)</option>
                            <option value="Red">Red</option>
                            <option value="Green">Green</option>
                            <option value="Blue">Blue</option>
                        </Form.Select>
                    </Form.Group>

                    {/* Action Buttons */}
                    <div className="d-grid gap-2 d-md-flex justify-content-md-start">
                        <Button type="submit" variant="primary">Add</Button>
                        <Button type="button" variant="warning" onClick={() => { handleSubmit((data) => handleFormSubmit(data, 'delete'))(); }}>Delete</Button>
                        <Button type="button" variant="success" onClick={() => { handleSubmit((data) => handleFormSubmit(data, 'update'))(); }}>Update</Button>
                    </div>
                </Form>
            </Container>
        </>
    )
}

export default AdminPortal;